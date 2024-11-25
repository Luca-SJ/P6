package com.openclassrooms.mddapi.Dtos;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    // Name or Email
    @NotEmpty(message = "The name can't be empty")
    private String nameOrEmail;

    @NotEmpty(message = "The password can't be empty")
    private String password;

    public UserDTO(String nameOrEmail, String password) {
        this.nameOrEmail = nameOrEmail;
        this.password = password;
    }

    public String getNameOrEmail() {
        return nameOrEmail;
    }

    public void setNameOrEmail(String name) {
        this.nameOrEmail = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
