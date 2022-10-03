package com.web.MyPetForApp.comment.repository;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"member", "board"})
    Page<Comment> findAllByMember(Member member, Pageable pageable);
    @EntityGraph(attributePaths = {"member", "board"})
    Page<Comment> findAllByBoard(Board board, Pageable pageable);
}
