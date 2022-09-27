package com.web.MyPetForApp.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.web.MyPetForApp.image.util.ImageUtils;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    @Value("${cloud.aws.s3.bucket.member}")
    private String memberFolder;

    @Value("${cloud.aws.s3.bucket.item}")
    private String itemFolder;

    private String bucket;

    private final AmazonS3 amazonS3;
    private final ImageUtils imageUtils;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private int putNum;

    private int objCnt;

    // 파일 업로드
    public List<String> uploadFile(List<MultipartFile> multipartFiles, String domain, String requestId, String way) {
        List<String> fileNameList = new ArrayList<>();

        if(domain.equals("member") && !memberRepository.existsMemberByMemberId(requestId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 회원은 존재하지 않습니다.");
        }

        if(domain.equals("item") && !itemRepository.existsItemByItemId(requestId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 상품은 존재하지 않습니다.");
        }

        bucket = assignFolder(domain);

        // 경로 안에 업로드하고자하는 회원/상품번호에 해당하는 파일 리스트를 불러온다.
        String bucketOrg = bucket.substring(0, 11);
        String suffix = bucket.substring(12);
        ListObjectsV2Result listObj = amazonS3.listObjectsV2(bucketOrg, suffix + "/" + requestId + "/" + way);
        List<S3ObjectSummary> objectSummaryList = listObj.getObjectSummaries();
        System.out.println("bucketOrg = " + bucketOrg);
        System.out.println("suffix = " + suffix);

        // 파일 리스트를 최근 수정 시간 내림차순으로 정렬
//        objectSummaryList.sort(new Comparator<S3ObjectSummary>() {
//            @Override
//            public int compare(S3ObjectSummary o1, S3ObjectSummary o2) {
//                if(o1.getLastModified().before(o2.getLastModified())) return -1;
//                else if (o2.getLastModified().before(o1.getLastModified())) return 1;
//                else return 0;
//            }
//        });

        objCnt = listObj.getKeyCount();
        System.out.println("objCnt = " + objCnt);

        putNum = objCnt + 1;

        multipartFiles.forEach(file -> {

            // 메인/프로필 이미지 처리 : 메인 이미지가 저장된 갯수가 1개 이상이면 그 상품들을 지워버리고 새로 업로드한다.
            if((way.equals("main") || way.equals("profile") )&& !objectSummaryList.isEmpty()) {
                objectSummaryList.stream().forEach(o ->
                       amazonS3.deleteObject(new DeleteObjectRequest(bucket, o.getKey().substring(8)))
                );
            }

            // 상세 이미지 처리 : 상세 이미지가 저장된 갯수가 5개 이상이면 가장 오래전에 업로드(수정)한 파일을 지우고 업로드한다. (수정)
            // 해당 상품의 이미지가 저장된 갯수가 5개 이상이면 5번 이미지를 지우고 업로드한다. 4
            if(way.equals("detail")&& objCnt >= 4) {
                amazonS3.deleteObject(new DeleteObjectRequest(bucket, objectSummaryList.get(objectSummaryList.size() - 1).getKey().substring(6)));
                putNum--;
            }

            System.out.println("putNum = " + putNum);
            // 업로드 시 저장할 파일 이름 생성
            String fileName = imageUtils.createFileName(requestId , file.getOriginalFilename(), way , putNum++);

            // S3에 업로드를 위한 세팅
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            // 파일 업로드 실시
            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                fileNameList.add(fileName);
                objCnt++;
                // 회원 : 한번에 이미지 1개씩만 업로드할 수 있다.
                if(domain.equals("member") && fileNameList.size() > 1) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업로드 가능 파일 개수는 1개입니다.");
                // 상품 : 한번에 이미지 5개씩만 업로드할 수 있다.
                } else if (domain.equals("item") && fileNameList.size() > 5) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업로드 가능 파일 개수는 최대 5개입니다.");
                }
                logger.info("upload " + domain + "requestId : {}", requestId);
                // 업로드 실패 시 처리
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
        });
        return fileNameList;
    }

    // 파일 다운로드
    public byte[] downloadFile(String fileName, String domain, String requestId) {
        bucket = assignFolder(domain);
        validateFileExists(fileName, domain, requestId);

        S3Object s3Object = amazonS3.getObject(bucket + "/" + requestId, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        s3Object.getKey();

        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 다운로드에 실패했습니다.");
        }
    }

    // 파일 삭제
    public void deleteFile(String fileName, String domain, String requestId) {
        bucket = assignFolder(domain);
        validateFileExists(fileName, domain, requestId);
        amazonS3.deleteObject(new DeleteObjectRequest(bucket + "/" + requestId, fileName));
    }

    // 해당 식별번호 파일 리스트 조회
    public List<String> findFilesById(String domain, String requestId) {
        bucket = assignFolder(domain);
        List<String> fileNameList = new ArrayList<>();

        // 경로 안에 업로드하고자하는 회원/상품번호에 해당하는 파일 리스트를 불러온다.
        String bucketOrg = bucket.substring(0, 11);
        String suffix = bucket.substring(12);
        ListObjectsV2Result listObj = amazonS3.listObjectsV2(bucketOrg, suffix + "/" + requestId);
        List<S3ObjectSummary> objectSummaryList = listObj.getObjectSummaries();
        System.out.println("objectSummaryList = " + objectSummaryList.size());
        // 파일 리스트를 최근 수정 시간 내림차순으로 정렬
//        objectSummaryList.sort(new Comparator<S3ObjectSummary>() {
//            @Override
//            public int compare(S3ObjectSummary o1, S3ObjectSummary o2) {
//                if(o1.getLastModified().before(o2.getLastModified())) return -1;
//                else if (o2.getLastModified().before(o1.getLastModified())) return 1;
//                else return 0;
//            }
//        });

        if(domain.equals("member")) {
            objectSummaryList.forEach(
                    file -> fileNameList.add(file.getKey().substring(8)));
        } else if (domain.equals("item")) {
            objectSummaryList.forEach(
                    file -> fileNameList.add(file.getKey().substring(6)));
        }
        return fileNameList;
    }

    // 파일 검증
    private void validateFileExists(String resourcePath, String domain, String requestId) {
        if(domain.equals("member")) bucket = memberFolder;
        else if(domain.equals("item")) bucket = itemFolder;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유효한 도메인이 아닙니다.");

        if (!amazonS3.doesObjectExist(bucket + "/" + requestId, resourcePath)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일이 존재하지 않습니다.");
        }
    }

    // 도메인 별로 구분해 디렉토리를 지정한다.
    private String assignFolder(String domain) {
        if(domain.equals("member")) bucket = memberFolder;
        else if(domain.equals("item")) bucket = itemFolder;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유효한 도메인이 아닙니다.");
        return bucket;
    }
}
