package com.web.MyPetForApp.qna.repository;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.qna.entity.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    @EntityGraph(attributePaths = "member")
    Page<Qna> findAllByItem(Item item, Pageable pageable);

}
