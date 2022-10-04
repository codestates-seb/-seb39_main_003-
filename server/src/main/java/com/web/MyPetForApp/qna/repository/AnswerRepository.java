package com.web.MyPetForApp.qna.repository;

import com.web.MyPetForApp.qna.entity.Answer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @EntityGraph(attributePaths = {"member", "member.nickName"})
    Optional<Answer> findByQuestionId(Long questionId);
}
