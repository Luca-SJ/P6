package com.openclassrooms.mddapi.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotEmpty(message = "The name can't be empty")
    private String name;

    @Email(message = "the email is not valid")
    private String email;

    @NotEmpty(message = "The password can't be empty")
    private String password;


}
