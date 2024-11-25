package com.openclassrooms.mddapi.Models;

import java.sql.Date;

public class NewsDTO {

    private long id;
    private Date created_at;
    private String description;
    private String title;
    private long owner_id;
    private String picture;
    private Date updated_at;

    public NewsDTO() {}

    public NewsDTO(String description, String title, long owner_id) {
        this.description = description;
        this.title = title;
        this.owner_id = owner_id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date newCreated_at) {
        this.created_at = newCreated_at;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
    public long getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String newPicture) {
        this.picture = newPicture;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date newUpdated_at) {
        this.updated_at = newUpdated_at;
    }
}
