package com.web.MyPetForApp.image.controller;

import com.web.MyPetForApp.image.mapper.ImageMapper;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.image.util.ImageUtils;
import com.web.MyPetForApp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/s3")
public class ImageController {
    private final ImageService imageService;
    private final ImageUtils imageUtils;
    private final ImageMapper imageMapper;
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @PostMapping("/file/member")
    public ResponseEntity uploadMemberFile(@RequestPart List<MultipartFile> multipartFiles,
                                           @RequestParam String requestCode) {
        List<String> fileNameList = imageService.uploadFile(multipartFiles, "member", requestCode);
        return new ResponseEntity<>(imageMapper.ImageListToImageDto(fileNameList) , HttpStatus.OK);
    }

    @DeleteMapping("/file/member")
    public ResponseEntity removeMemberFile(@RequestParam String storedFileName) {
        imageService.deleteFile(storedFileName, "member");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/file/member")
    public ResponseEntity downloadMemberFile(@RequestParam String storedFileName) {
        byte[] data = imageService.downloadFile(storedFileName, "member");
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = imageUtils.buildHeaders(storedFileName, data, imageUtils.getFileExtension(storedFileName));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/file/item")
    public ResponseEntity uploadItemFile(@RequestPart List<MultipartFile> multipartFiles,
                                         @RequestParam String  requestCode) {
        List<String> fileNameList = imageService.uploadFile(multipartFiles, "item", requestCode);
        return new ResponseEntity<>(imageMapper.ImageListToImageDto(fileNameList) , HttpStatus.OK);
    }

    @DeleteMapping("/file/item")
    public ResponseEntity removeItemFile(@RequestParam String storedFileName) {
        imageService.deleteFile(storedFileName, "item");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/file/item")
    public ResponseEntity downloadItemFile(@RequestParam String storedFileName) {
        byte[] data = imageService.downloadFile(storedFileName, "item");
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = imageUtils.buildHeaders(storedFileName, data, imageUtils.getFileExtension(storedFileName));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
