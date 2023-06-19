package com.company.springbootblogrestapi.service.impl;

import com.company.springbootblogrestapi.entity.Comment;
import com.company.springbootblogrestapi.entity.Post;
import com.company.springbootblogrestapi.exception.NotBelongException;
import com.company.springbootblogrestapi.exception.ResourceNotFoundException;
import com.company.springbootblogrestapi.payload.CommentDto;
import com.company.springbootblogrestapi.repository.CommentRepository;
import com.company.springbootblogrestapi.repository.PostRepository;
import com.company.springbootblogrestapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToCommentEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToCommentDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToCommentDto).toList();
    }

    @Override
    public CommentDto findCommentByPostIdAndCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new NotBelongException(HttpStatus.BAD_REQUEST, "This comment doesn't belong to post");
        }
        return mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new NotBelongException(HttpStatus.BAD_REQUEST, "This comment doesn't belong to post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new NotBelongException(HttpStatus.BAD_REQUEST, "This comment doesn't belong to post");
        }
        commentRepository.delete(comment);
    }

    private CommentDto mapToCommentDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToCommentEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}