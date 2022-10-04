package com.web.MyPetForApp.qna.mapper;

import com.web.MyPetForApp.qna.dto.QuestionDto;
import com.web.MyPetForApp.qna.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public Question questionPostDtoToQuestion(QuestionDto.Post requestBody){
        return Question.builder()
                .questionTitle(requestBody.getQuestionTitle())
                .questionContent(requestBody.getQuestionContent())
                .build();
    }
    public Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody){
        return Question.builder()
                .questionTitle(requestBody.getQuestionTitle())
                .questionContent(requestBody.getQuestionContent())
                .build();
    }

    public QuestionDto.Response questionToQuestionResponseDto(Question question){
        return QuestionDto.Response.builder()
                .questionId(question.getQuestionId())
                .checked(question.isChecked())
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .createAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .nickName(question.getMember().getNickName())
                .itemId(question.getItem().getItemId())
                .build();
    }
    public List<QuestionDto.Response> questionsToQuestionResponseDto(List<Question> questions){
        return questions
                .stream()
                .map(question -> QuestionDto.Response
                        .builder()
                        .questionId(question.getQuestionId())
                        .checked(question.isChecked())
                        .questionTitle(question.getQuestionTitle())
                        .questionContent(question.getQuestionContent())
                        .createAt(question.getCreatedAt())
                        .modifiedAt(question.getModifiedAt())
                        .nickName(question.getMember().getNickName())
                        .itemId(question.getItem().getItemId())
                        .build())
                .collect(Collectors.toList());
    }
}
