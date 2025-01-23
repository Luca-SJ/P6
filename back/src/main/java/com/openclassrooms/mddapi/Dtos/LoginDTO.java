package com.openclassrooms.mddapi.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {

    // Name or Email
    @NotEmpty(message = "The name can't be empty")
    private String nameOrEmail;

    @NotEmpty(message = "The password can't be empty")
    private String password;


}
