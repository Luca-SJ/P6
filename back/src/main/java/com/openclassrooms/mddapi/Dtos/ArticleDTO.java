package com.openclassrooms.mddapi.Dtos;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class ArticleDTO {

    private long id;
    private LocalDateTime created_at;
    private String description;
    private String title;
    private String owner_name;
    private String owner_id;
    private String topic_id;
    private String picture;
    private LocalDateTime updated_at;

}
