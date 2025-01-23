package com.openclassrooms.mddapi.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDTO {
    @NotNull
    @JsonProperty("user_id")
    private Long userID;
    @JsonProperty("user_name")
    private String userName;
    @NotEmpty
    private String message;
    @NotNull
    @JsonProperty("article_id")
    private Long articleID;
}
