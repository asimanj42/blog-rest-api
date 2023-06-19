package com.company.springbootblogrestapi.mapper;

import com.company.springbootblogrestapi.entity.Comment;
import com.company.springbootblogrestapi.payload.CommentDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final ModelMapper modelMapper;
    public CommentDto mapToCommentDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
    public Comment mapToCommentEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}
