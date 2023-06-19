package com.company.springbootblogrestapi.mapper;

import com.company.springbootblogrestapi.entity.Post;
import com.company.springbootblogrestapi.payload.PostDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final ModelMapper modelMapper;
    public PostDto mapToPostDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
    public Post mapToPostEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
