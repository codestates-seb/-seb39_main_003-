package com.web.MyPetForApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(name = "단일 데이터 반환 모델")
@AllArgsConstructor
@Getter
public class SingleResponseDto<T> {
    @Schema(description = "객체 데이터")
    private T data;
}
