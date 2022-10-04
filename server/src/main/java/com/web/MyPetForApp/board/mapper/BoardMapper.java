package com.web.MyPetForApp.board.mapper;

import com.web.MyPetForApp.board.dto.BoardDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardCategory;
import com.web.MyPetForApp.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BoardMapper {

    public Board boardPostToBoard(BoardDto.Post post){

        return Board.builder()
                .title(post.getTitle())
                .boardContent(post.getBoardContents())
                .build();
    }

    public Board boardPatchToBoard(BoardDto.Patch patch){
        return Board.builder()
                .title(patch.getTitle())
                .boardContent(patch.getBoardContents())
                .build();
    }

    public BoardDto.Detail boardToBoardDetail(Board board){
        return BoardDto.Detail.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .boardContents(board.getBoardContent())
                .view(board.getView())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .nickName(board.getMember().getNickName())
                .build();
    }

    public List<BoardDto.Response> boardToBoardResponse(List<Board> boards){
        return boards.stream()
                .map(board ->
                        BoardDto.Response.builder()
                                .boardId(board.getBoardId())
                                .title(board.getTitle())
                                .view(board.getView())
                                .modifiedAt(board.getModifiedAt())
                                .nickName(board.getMember().getNickName())
                                .build()
                ).collect(Collectors.toList());
    }
}
