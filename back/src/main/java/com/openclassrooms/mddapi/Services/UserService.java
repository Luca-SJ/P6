package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.LoginDTO;
import com.openclassrooms.mddapi.Dtos.MessageDTO;
import com.openclassrooms.mddapi.Dtos.RegisterDTO;
import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Mappers.UserMapper;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repository.UserRepository;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    public UserDTO findByEmailOrName(String email) throws ResourceNotFoundException {

        final User user = userRepository.findByEmailOrName(email, email)
                .orElseThrow(()-> new ResourceNotFoundException("utilisateur avec ID : " + email + " inexistant"));

        return this.userMapper.toUserDTO(user);
    }

    public UserDTO findById(long id) throws ResourceNotFoundException {

        final User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("utilisateur avec ID : " + id + " inexistant"));

        return this.userMapper.toUserDTO(user);
    }


    public void createUser(RegisterDTO userDTO) {
        final User user = this.userMapper.toUser(userDTO);
        final String password = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public boolean authenticate(LoginDTO userDTO) throws UsernameNotFoundException {
        return userRepository.findByEmailOrName(userDTO.getNameOrEmail(), userDTO.getNameOrEmail())
                .map(user -> passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) // Comparer les mots de passe
                .orElse(false);
    }

    @Override
    @Transactional
    public MessageDTO updateUser(UserDTO userUpdated, String usernameFromJWT) throws ResourceNotFoundException {
        User user = userRepository.findByEmailOrName(usernameFromJWT, usernameFromJWT).orElse(null);
        if (user != null) {
            if (userUpdated.getName() != null) {
                user.setName(userUpdated.getName());
            }
            if (userUpdated.getEmail() != null) {
                user.setEmail(userUpdated.getEmail());
            }
            if (userUpdated.getPassword() != null) {
                final String password = passwordEncoder.encode(userUpdated.getPassword());
                user.setPassword(password);
            }

            userRepository.save(user);
            return new MessageDTO("Mise à jour effectuée");
        }
        throw new ResourceNotFoundException("Not found");
    }
}
