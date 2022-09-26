package com.web.MyPetForApp.image.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
    List<String> fileNameList;
}
