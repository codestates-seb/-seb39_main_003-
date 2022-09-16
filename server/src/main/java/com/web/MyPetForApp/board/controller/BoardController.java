package com.web.MyPetForApp.board.controller;

import com.web.MyPetForApp.board.dto.BoardDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.mapper.BoardMapper;
import com.web.MyPetForApp.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardMapper boardMapper;
    private final BoardService boardService;

    public BoardController(BoardMapper boardMapper, BoardService boardService){
        this.boardMapper = boardMapper;
        this.boardService = boardService;
    }
    @PostMapping("/write")
    public ResponseEntity write(@RequestBody BoardDto.Post post){
        Board board =boardMapper.boardPostToBoard(post);
        boardService.write(board, post.getTagIds());
        return null;
    }
}
