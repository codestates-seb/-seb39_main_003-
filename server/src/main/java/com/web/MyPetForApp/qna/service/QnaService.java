package com.web.MyPetForApp.qna.service;

import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.qna.dto.AnswerDto;
import com.web.MyPetForApp.qna.entity.Answer;
import com.web.MyPetForApp.qna.entity.Question;
import com.web.MyPetForApp.qna.repository.AnswerRepository;
import com.web.MyPetForApp.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class QnaService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public Question createQuestion(Question question, String memberId, String itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        question.changeItem(item);
        question.changeMember(member);
        return questionRepository.save(question);
    }

    public Question findQuestion(Long qnaId){
        return findVerifiedQuestion(qnaId);
    }

    public Page<Question> findQuestions(String itemId, int page, int size){
        Item item = itemService.findVerifiedItem(itemId);
        return questionRepository.findAllByItem(item, PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }

    public Question updateQuestion(Long qnaId, Question question, String memberId){
        Question findQuestion = findVerifiedQuestion(qnaId);
        if(!memberId.equals(findQuestion.getMember().getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_QNA);
        }
        findQuestion.updateQna(question);
        return findQuestion;
    }

    @Transactional
    public void deleteQuestion(Long qnaId){
        Question question = findVerifiedQuestion(qnaId);
        questionRepository.delete(question);
        Optional.ofNullable(answerRepository.findByQuestionId(qnaId))
                .ifPresent(answer -> answerRepository.delete(answer.get()));
    }
// ----------------------------------------------------------------------------------------------------

    @Transactional
    public Answer createAnswer(Answer answer){
        findVerifiedQuestion(answer.getQuestionId());
        memberService.findVerifiedMember(answer.getMember().getMemberId());
        questionRepository.updateChecked(true, answer.getQuestionId());

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(AnswerDto.Post patch){
        findVerifiedQuestion(patch.getQuestionId());

        Answer answer = answerRepository.findByQuestionId(patch.getQuestionId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        answer.setMember(memberService.findVerifiedMember(patch.getMemberId()));

        Optional.ofNullable(patch.getAnswerContent())
                .ifPresent(content -> answer.setAnswerContent(content));

        return answerRepository.save(answer);
    }

    public Answer findAnswer(Long questionId){
        findVerifiedQuestion(questionId);

        Optional<Answer> answer = answerRepository.findByQuestionId(questionId);

        return answer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    @Transactional
    public void deleteAnswer(Long answerId){
        Answer answer = findVerifiedAnswer(answerId);
        questionRepository.updateChecked(false, answer.getQuestionId());
        answerRepository.delete(answer);
    }


// ----------------------------------------------------------------------------------------------------
    public Question findVerifiedQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }
    public Answer findVerifiedAnswer(Long answerId){
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }
}
