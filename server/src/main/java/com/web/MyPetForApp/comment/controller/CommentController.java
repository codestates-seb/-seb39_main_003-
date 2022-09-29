package com.web.MyPetForApp.comment.controller;

import com.web.MyPetForApp.comment.dto.CommentDto;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.comment.mapper.CommentMapper;
import com.web.MyPetForApp.comment.service.CommentService;
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

@Tag(name = "게시판에 대한 댓글 API(커뮤니티, 공지사항, FAQ 통합)")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }
    @Operation(summary = "댓글 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentDto.Post post) {
        Comment comment = commentMapper.commentPostToComment(post);
        commentService.create(comment);

        return new ResponseEntity<>("Create Success", HttpStatus.OK);
    }

    @Operation(summary = "댓글 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId,
                                        @RequestBody CommentDto.Patch patch) {
        commentService.update(commentId, patch);

        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }
    @Operation(summary = "댓글 데이터 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable long commentId) {

        commentService.deleteComment(commentId);

        return new ResponseEntity("Delete Success", HttpStatus.OK);
    }

    @Operation(summary = "댓글 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity getComments(@PathVariable Long boardId,
                                      @PathVariable String memberId,
                                      @Parameter(description = "댓글 조회 기준", example = "boards") @RequestParam("where") String where,
                                      @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                      @Parameter(description = "한 페이지 당 게시글 수") @RequestParam(required = false, defaultValue = "10") int size) {
        System.out.println(where);

        Page<Comment> pageComment = commentService.getComments(where, boardId, memberId, page - 1, size);
        List<Comment> comments = pageComment.getContent();
        List<CommentDto.Response> responses = commentMapper.commentToCommentResponse(comments);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
