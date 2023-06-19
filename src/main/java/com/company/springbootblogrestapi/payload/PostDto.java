package com.company.springbootblogrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 3 characters")
    private String title;
    @NotEmpty
    @Size(min = 5, message = "Post description should have at least 5 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;


}
