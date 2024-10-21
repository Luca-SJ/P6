package com.openclassrooms.mddapi.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;

    public Theme() {}

    public Theme(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date newCreated_at) {
        this.created_at = newCreated_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date newUpdated_at) {
        this.updated_at = newUpdated_at;
    }
}
