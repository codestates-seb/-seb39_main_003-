package com.web.MyPetForApp.board.controller;

import com.web.MyPetForApp.board.dto.BoardCategoryDto;
import com.web.MyPetForApp.board.dto.BoardDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.mapper.BoardMapper;
import com.web.MyPetForApp.board.service.BoardService;
import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시판 API(커뮤니티, 공지사항, FAQ 통합)")
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardMapper boardMapper;
    private final BoardService boardService;

    public BoardController(BoardMapper boardMapper, BoardService boardService){
        this.boardMapper = boardMapper;
        this.boardService = boardService;
    }

    @Operation(summary = "게시글 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity createBoard(@RequestBody BoardDto.Post post){
        Board board = boardMapper.boardPostToBoard(post);
        boardService.create(board, post.getMemberId(), post.getCategoryId());
        return new ResponseEntity<>("create success", HttpStatus.OK);
    }

    @Operation(summary = "게시글 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping({"/{boardId}"})
    public ResponseEntity updateBoard(@PathVariable Long boardId,
                                      @RequestBody BoardDto.Patch patch){
        boardService.update(boardId, boardMapper.boardPatchToBoard(patch));

        return new ResponseEntity<>("update success", HttpStatus.OK);
    }

    @Operation(summary = "게시글 데이터 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId){
        boardService.delete(boardId);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
    @Operation(summary = "게시글 리스트 조회", description = "카테고리 별로 게시판/공지사항/FAQ를 조회 가능")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping
    public ResponseEntity searchBoards(@Parameter(description = "카테고리 탭 식별번호", example = "1") @RequestParam Long categoryId,
                                       @Parameter(description = "검색 키워드(값이 없으면 해당 탭 전체 조회") @RequestParam(required = false, defaultValue = "") String keyword,
                                       @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                       @Parameter(description = "해당 페이지 게시글 수") @RequestParam(required = false, defaultValue = "10") int size){
        Page<Board> pageBoards = boardService.searchBoards(categoryId, keyword, page-1, size);
        List<Board> boards = pageBoards.getContent();
        List<BoardDto.Response> responses = boardMapper.boardToBoardResponse(boards);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageBoards), HttpStatus.OK);
    }

//    @Operation(summary = "게시판 리스트 조회")
//    @ApiResponses(
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "OK"
//            )
//    )
//    @GetMapping
//    public ResponseEntity getBoardList(@Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
//                                       @Parameter(description = "한 페이지 당 게시글 수") @RequestParam(required = false, defaultValue = "10") int size,
//                                       @Parameter(description = "카테고리 아이디") @RequestParam Long categoryId){
//        Page<Board> pageBoards = boardService.getBoard(categoryId, page-1, size);
//        List<Board> boards = pageBoards.getContent();
//        List<BoardDto.Response> responses = boardMapper.boardToBoardResponse(boards);
//
//        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageBoards), HttpStatus.OK);
//    }

    @Operation(summary = "하나의 게시글 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/{boardId}")
    public ResponseEntity getBoard(@PathVariable Long boardId){

        BoardDto.Detail detail = boardMapper.boardToBoardDetail(boardService.selectBoard(boardId));

        return new ResponseEntity<>(new SingleResponseDto<>(detail), HttpStatus.OK);
    }
    @Operation(summary = "해당 상위 카테고리의 세부 태그 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/categories/{pid}")
    public ResponseEntity getCategories(@PathVariable Integer pid){
        List<BoardCategoryDto.Response> responses = boardService.getCategories(pid);

        return new ResponseEntity<>(new SingleResponseDto<>(responses), HttpStatus.OK);
    }
}
