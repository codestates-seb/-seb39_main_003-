package com.web.MyPetForApp.image.mapper;

import com.web.MyPetForApp.image.dto.ImageDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageMapper {
    public ImageDto ImageListToImageDto(List<String> filenameList) {
        return ImageDto.builder().fileNameList(filenameList).build();
    }
}
