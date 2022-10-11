package com.web.MyPetForApp.board.repository;

import com.web.MyPetForApp.board.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
    List<BoardCategory> findAllByPid(Integer pid);
}
