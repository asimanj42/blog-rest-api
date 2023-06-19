package com.company.springbootblogrestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 5,message = "Comment body should have at least 5 characters")
    private String body;
}
