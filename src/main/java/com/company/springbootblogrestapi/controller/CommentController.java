package com.company.springbootblogrestapi.controller;

import com.company.springbootblogrestapi.payload.CommentDto;
import com.company.springbootblogrestapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        return ResponseEntity.ok(commentService.findCommentByPostIdAndCommentId(postId, commentId));
    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId, @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
