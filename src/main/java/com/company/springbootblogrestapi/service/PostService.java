package com.company.springbootblogrestapi.service;

import com.company.springbootblogrestapi.payload.PostDto;
import com.company.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    List<PostDto> getPostsByCategoryId(Long categoryId);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);


    void deletePost(long id);
}
