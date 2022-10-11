package com.web.MyPetForApp.image.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Schema(name = "ImageDto", description = "이미지 데이터 반환 모델")
public class ImageDto {
    @Schema(description = "이미지 파일 이름 리스트")
    List<String> fileNameList;
}
