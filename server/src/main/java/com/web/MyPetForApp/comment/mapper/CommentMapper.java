package com.web.MyPetForApp.comment.mapper;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.comment.dto.CommentDto;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {
    public Comment commentPostToComment(CommentDto.Post post){
        Member member = Member.builder()
                .memberId(post.getMemberId())
                .build();
        Board board = Board.builder()
                .boardId(post.getBoardId())
                .build();

        return Comment.builder()
                .commentContent(post.getCommentContent())
                .member(member)
                .board(board)
                .build();
    }

    public List<CommentDto.Response> commentToCommentResponse(List<Comment> comments){
        return comments.stream()
                .map(comment ->
                        CommentDto.Response.builder()
                                .boardId(comment.getBoard().getBoardId())
                                .nickName(comment.getMember().getNickName())
                                .commentContent(comment.getCommentContent())
                                .modifiedAt(comment.getModifiedAt())
                                .build()
                ).collect(Collectors.toList());
    }
}
