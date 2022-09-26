package com.web.MyPetForApp.comment.controller;

import com.web.MyPetForApp.comment.dto.CommentDto;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.comment.mapper.CommentMapper;
import com.web.MyPetForApp.comment.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentDto.Post post) {
        Comment comment = commentMapper.commentPostToComment(post);
        commentService.create(comment);

        return new ResponseEntity<>("Create Success", HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId,
                                        @RequestBody CommentDto.Patch patch) {
        commentService.update(commentId, patch);

        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable long commentId) {

        commentService.deleteComment(commentId);

        return new ResponseEntity("Delete Success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getComments(@PathVariable Long id,
                                      @RequestParam("where") String where,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size) {
        System.out.println(where);

        Page<Comment> pageComment = commentService.getComments(where, id, page - 1, size);
        List<Comment> comments = pageComment.getContent();
        List<CommentDto.Response> responses = commentMapper.commentToCommentResponse(comments);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
