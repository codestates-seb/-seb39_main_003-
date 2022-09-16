package com.web.MyPetForApp.board.repository;

import com.web.MyPetForApp.board.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
