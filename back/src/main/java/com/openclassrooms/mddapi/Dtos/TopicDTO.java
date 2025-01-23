package com.openclassrooms.mddapi.Dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class TopicDTO {
    private long id;
    private LocalDateTime created_at;
    private String description;
    private String name;
    private LocalDateTime updated_at;
}
