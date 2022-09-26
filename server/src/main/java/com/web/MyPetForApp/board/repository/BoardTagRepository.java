package com.web.MyPetForApp.board.repository;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM board_tag WHERE board_id = :boardId ")
    void deleteAllByBoard(@Param(value = "boardId") Long boardId);
}