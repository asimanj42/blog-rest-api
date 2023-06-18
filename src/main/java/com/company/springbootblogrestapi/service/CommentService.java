package com.company.springbootblogrestapi.service;

import com.company.springbootblogrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto findCommentByPostIdAndCommentId(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
