package com.web.MyPetForApp.image.util;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class ImageUtils {

    public String  createFileName(String requestCode ,String fileName) {
        // 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌린다.
        return requestCode + "_" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    public String getFileExtension(String fileName) {
        // 파일 형식이 잘못된 경우 체크
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ")입니다.");
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
                memberIdWithFileName.lastIndexOf("_"), memberIdWithFileName.lastIndexOf(".") + 1
        );
        return ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();
    }
}
