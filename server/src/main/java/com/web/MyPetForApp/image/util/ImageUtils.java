package com.web.MyPetForApp.image.util;

import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.exception.FileLogicException;
import lombok.Getter;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class ImageUtils {

    public String  createFileName(String requestCode ,String fileName, String way, int sequence) {
        // 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌린다. (예전 버전)
//        return requestCode + "/" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
        // 식별코드를 통해 폴더 생성_ 메인 이미지/ 디테일 이미지_이미지 순서를 결정한다.
        int num = sequence != 0 ? sequence : 1;
        if(way.equals("profile") || way.equals("main")) num = 1;
        return requestCode + "/" + way + "_" + num + getFileExtension(fileName);
    }

    public String getFileExtension(String fileName) {
        // 파일 형식이 잘못된 경우 체크
        try {
            String extension = fileName.substring(fileName.lastIndexOf("."));
            // 확장자가 우리가 선언한 확장자인지 체크
            FileExtension.valueOf(extension);
            return extension;
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new FileLogicException(ExceptionCode.EXTENSION_IS_INVALID);
        }
    }

    public HttpHeaders buildHeaders(String storedFileName, byte[] data, String extension) {
        MediaType mediaType = extension == "png" ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(data.length);
        headers.setContentType(mediaType);
        headers.setContentDisposition(createContentDisposition(storedFileName));
        return headers;
    }

    public static ContentDisposition createContentDisposition(String memberIdWithFileName) {
        String fileName = memberIdWithFileName.substring(
                0, memberIdWithFileName.lastIndexOf(".") + 1
        );
        return ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();
    }

    public enum FileExtension {

        PNG("png"),
        JPEG("jpeg"),
        JPG("jpg");

        @Getter
        private final String extensionName;

        FileExtension(String extensionName) {
            this.extensionName = extensionName;
        }
    }
}
