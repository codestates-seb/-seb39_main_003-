package com.web.MyPetForApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(name = "복합 데이터 반환 모델")
@Getter
public class MultiResponseDto<T> {
    @Schema(description = "객체 데이터")
    private List<T> data;
    @Schema(description = "페이지 데이터")
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
