package com.web.MyPetForApp.board.repository;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM board WHERE board_id IN" +
                    "(SELECT board_id FROM board_tag WHERE tag_id IN (:tagIds)" +
                    "GROUP BY board_id HAVING COUNT(board_id) = :size)")
    Page<Board> findAllByTags(@Param(value = "tagIds") List<Long> tagIds,
                              @Param(value = "size") Integer size,
                              Pageable pageable);
}
