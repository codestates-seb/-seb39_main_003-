package com.web.MyPetForApp.comment.mapper;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.comment.dto.CommentDto;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment commentPostToComment(CommentDto.Post post){
        Member member = new Member();
        member.setMemberId(post.getMemberId());
        Board board = new Board();
        board.setBoardId(post.getBoardId());

        return Comment.builder()
                .commentContent(post.getCommentContent())
                .member(member)
                .board(board)
                .build();
    }
}
