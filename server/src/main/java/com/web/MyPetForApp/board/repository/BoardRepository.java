package com.web.MyPetForApp.board.repository;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"member"})
    Page<Board> findAllByBoardCategory(BoardCategory boardCategory, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"member"})
    Optional<Board> findById(Long boardId);

    @EntityGraph(attributePaths = {"member"})
    Page<Board> findByBoardCategoryAndTitleContaining(BoardCategory boardCategory,
                                                      String keyword,
                                                      Pageable pageable);
}
