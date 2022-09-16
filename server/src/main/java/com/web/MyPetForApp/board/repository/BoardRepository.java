package com.web.MyPetForApp.board.repository;

import com.web.MyPetForApp.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
