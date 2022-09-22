package com.web.MyPetForApp.board.controller;

import com.web.MyPetForApp.board.dto.BoardDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.mapper.BoardMapper;
import com.web.MyPetForApp.board.responseDto.MultiResponseDto;
import com.web.MyPetForApp.board.responseDto.SingleResponseDto;
import com.web.MyPetForApp.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardMapper boardMapper;
    private final BoardService boardService;

    public BoardController(BoardMapper boardMapper, BoardService boardService){
        this.boardMapper = boardMapper;
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity createBoard(@RequestBody BoardDto.Post post){
        Board board = boardMapper.boardPostToBoard(post);
        boardService.create(board, post.getTagIds(), post.getMemberId());
        return new ResponseEntity<>("create success", HttpStatus.OK);
    }

    @PatchMapping({"/{boardId}"})
    public ResponseEntity updateBoard(@PathVariable Long boardId,
                                      @RequestBody BoardDto.Patch patch){
        boardService.update(boardId, boardMapper.boardPatchToBoard(patch), patch.getTagIds());
        return new ResponseEntity<>("update success", HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId){
        boardService.delete(boardId);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBoards(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestBody List<Long> tagIds){
        Page<Board> pageBoards = boardService.getBoard(tagIds, page-1, size);
        List<Board> boards = pageBoards.getContent();
        List<BoardDto.Response> responses = boardMapper.boardToBoardResponse(boards);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageBoards), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity selectBoard(@PathVariable Long boardId){

        BoardDto.Detail detail = boardMapper.boardToBoardDetail(boardService.selectBoard(boardId));

        return new ResponseEntity<>(new SingleResponseDto<>(detail), HttpStatus.OK);
    }
}
