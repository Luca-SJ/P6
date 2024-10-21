package com.openclassrooms.mddapi.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    private long idTheme;
    private Date created_at;

    public Subscription() {}

    public Subscription(long IdUser, long IdTheme) {
        this.idUser = IdUser;
        this.idTheme = IdTheme;
    }

    public long getIdUser() {
        return idUser;
    }
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdTheme() {
        return idTheme;
    }
    public void setIdTheme(long idTheme) {
        this.idTheme = idTheme;
    }

    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date newCreated_at) {
        this.created_at = newCreated_at;
    }
}
