package com.company.springbootblogrestapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog REST API",
                version = "1.0",
                description = "Spring Boot Blog REST API"
        )
)
public class SpringbootBlogRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
    }

}
