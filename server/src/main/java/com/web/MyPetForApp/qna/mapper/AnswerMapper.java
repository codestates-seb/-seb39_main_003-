package com.web.MyPetForApp.qna.mapper;

import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.qna.dto.AnswerDto;
import com.web.MyPetForApp.qna.entity.Answer;
import com.web.MyPetForApp.qna.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    public Answer answerPostToAnswer(AnswerDto.Post post){
        Member member = Member.builder()
                .memberId(post.getMemberId())
                .build();

        return Answer.builder()
                .answerContent(post.getAnswerContent())
                .questionId(post.getQuestionId())
                .member(member)
                .build();
    }

    public AnswerDto.Response answerToAnswerResponse(Answer answer){
        return AnswerDto.Response.builder()
                .answerId(answer.getAnswerId())
                .questionId(answer.getQuestionId())
                .answerConetent(answer.getAnswerContent())
                .nickName(answer.getMember().getNickName())
                .modifiedAt(answer.getModifiedAt())
                .build();
    }
}
