package com.web.MyPetForApp.image.controller;

import com.web.MyPetForApp.image.mapper.ImageMapper;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.image.util.ImageUtils;
import com.web.MyPetForApp.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "이미지 API", description = "회원/상품의 이미지 업로드, 다운로드, 삭제가 가능합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/s3")
public class ImageController {
    private final ImageService imageService;
    private final ImageUtils imageUtils;
    private final ImageMapper imageMapper;
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Operation(summary = "회원 이미지 업로드")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PostMapping(value ="/file/member", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadMemberFile(@RequestPart List<MultipartFile> multipartFiles,
                                           @Parameter(description = "식별번호") @RequestParam String requestId,
                                           @Parameter(description = "이미지 용도", example = "profile") @RequestParam String way) {
        List<String> fileNameList = imageService.uploadFile(multipartFiles, "member", requestId, way);
        return new ResponseEntity<>(imageMapper.ImageListToImageDto(fileNameList) , HttpStatus.OK);
    }

    @Operation(summary = "회원 이미지 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "NO_CONTENT"
            )
    )
    @DeleteMapping("/file/member")
    public ResponseEntity removeMemberFile(@Parameter(description = "저장된 이미지 파일 이름") @RequestParam String storedFileName,
                                           @Parameter(description = "식별번호") @RequestParam String requestId) {
        imageService.deleteFile(storedFileName, "member", requestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "회원 이미지 다운로드")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/file/member")
    public ResponseEntity downloadMemberFile(@Parameter(description = "저장된 이미지 파일 이름") @RequestParam String storedFileName,
                                             @Parameter(description = "식별번호") @RequestParam String requestId) {
        byte[] data = imageService.downloadFile(storedFileName, "member", requestId);
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = imageUtils.buildHeaders(storedFileName, data, imageUtils.getFileExtension(storedFileName));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
    @Operation(summary = "상품 이미지 업로드")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PostMapping("/file/item")
    public ResponseEntity uploadItemFile(@RequestPart List<MultipartFile> multipartFiles,
                                         @Parameter(description = "식별번호") @RequestParam String  requestId,
                                         @Parameter(description = "이미지 용도", example = "main") @RequestParam String way) {
        List<String> fileNameList = imageService.uploadFile(multipartFiles, "item", requestId, way);
        return new ResponseEntity<>(imageMapper.ImageListToImageDto(fileNameList) , HttpStatus.OK);
    }

    @Operation(summary = "상품 이미지 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "NO_CONTENT"
            )
    )
    @DeleteMapping("/file/item")
    public ResponseEntity removeItemFile(@Parameter(description = "저장된 이미지 파일 이름") @RequestParam String storedFileName,
                                         @Parameter(description = "식별번호") @RequestParam String requestId) {
        imageService.deleteFile(storedFileName, "item", requestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "회원 이미지 다운로드")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/file/item")
    public ResponseEntity downloadItemFile(@Parameter(description = "저장된 이미지 파일 이름") @RequestParam String storedFileName,
                                           @Parameter(description = "식별번호") @RequestParam String requestId) {
        byte[] data = imageService.downloadFile(storedFileName, "item", requestId);
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = imageUtils.buildHeaders(storedFileName, data, imageUtils.getFileExtension(storedFileName));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
