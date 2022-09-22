package com.web.MyPetForApp.comment.repository;

import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByMember(Member member, Pageable pageable);
}
