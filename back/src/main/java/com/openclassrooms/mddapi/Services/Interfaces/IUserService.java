package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Dtos.LoginDTO;
import com.openclassrooms.mddapi.Dtos.MessageDTO;
import com.openclassrooms.mddapi.Dtos.RegisterDTO;
import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface IUserService {

    boolean authenticate(LoginDTO user);

    UserDTO findByEmailOrName(String emailOrName) throws ResourceNotFoundException;

    UserDTO findById(long id) throws ResourceNotFoundException;
    void createUser(RegisterDTO user);

    MessageDTO updateUser(UserDTO userUpdated, String usernameFromJWT) throws ResourceNotFoundException;

}
