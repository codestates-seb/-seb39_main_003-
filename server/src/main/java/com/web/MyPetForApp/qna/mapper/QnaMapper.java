package com.web.MyPetForApp.qna.mapper;

import com.web.MyPetForApp.qna.dto.QnaDto;
import com.web.MyPetForApp.qna.entity.Qna;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QnaMapper {

    public Qna qnaPostDtoToQna(QnaDto.Post requestBody){
        return Qna.builder()
                .qnaTitle(requestBody.getQnaTitle())
                .qnaContent(requestBody.getQnaContent())
                .build();
    }
    public Qna qnaPatchDtoToQna(QnaDto.Patch requestBody){
        return Qna.builder()
                .qnaTitle(requestBody.getQnaTitle())
                .qnaContent(requestBody.getQnaContent())
                .build();
    }

    public QnaDto.Response qnaToQnaResponseDto(Qna qna){
        return QnaDto.Response.builder()
                .qnaId(qna.getQnaId())
                .checked(qna.isChecked())
                .qnaTitle(qna.getQnaTitle())
                .qnaContent(qna.getQnaContent())
                .createAt(qna.getCreatedAt())
                .modifiedAt(qna.getModifiedAt())
                .memberName(qna.getMember().getNickName())
                .itemId(qna.getItem().getItemId())
                .build();
    }
    public List<QnaDto.Response> qnasToQnaResponseDto(List<Qna> qnas){
        return qnas
                .stream()
                .map(qna -> QnaDto.Response
                        .builder()
                        .qnaId(qna.getQnaId())
                        .checked(qna.isChecked())
                        .qnaTitle(qna.getQnaTitle())
                        .qnaContent(qna.getQnaContent())
                        .createAt(qna.getCreatedAt())
                        .modifiedAt(qna.getModifiedAt())
                        .memberName(qna.getMember().getNickName())
                        .itemId(qna.getItem().getItemId())
                        .build())
                .collect(Collectors.toList());
    }
}
