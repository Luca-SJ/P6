package com.openclassrooms.mddapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String title;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;
    private String picture;
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;


}
