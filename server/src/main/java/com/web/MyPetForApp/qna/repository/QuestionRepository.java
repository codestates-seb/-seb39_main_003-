package com.web.MyPetForApp.qna.repository;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.qna.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByItem(Item item, Pageable pageable);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE question SET checked = :check WHERE question_id = :questionId" )
    void updateChecked(@Param(value = "check") Boolean check,
                       @Param(value = "questionId") Long questionId);
}
