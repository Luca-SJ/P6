package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repository.UserRepository;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByID(Long UserID) throws ResourceNotFoundException {
        return userRepository.findById(UserID)
                .orElseThrow(()->new ResourceNotFoundException("utilisateur avec ID : " + UserID + " inexistant"));
    }

    public User findByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmailOrName(email, email)
                .orElseThrow(()->new ResourceNotFoundException("utilisateur avec ID : " + email + " inexistant"));
    }

    public void deleteByID(Long UserID) throws ResourceNotFoundException {
        User user = userRepository.findById(UserID)
                .orElseThrow(()->new ResourceNotFoundException("utilisateur avec ID : " + UserID + " inexistant"));
        userRepository.delete(user);
    }

    public User updateUserByID(Long UserID, User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(UserID)
                .orElseThrow(()->new ResourceNotFoundException("utilisateur avec ID : "+ UserID + " inexistant"));

        user.setName(userDetails.getName());
        user.setCreated_at(userDetails.getCreated_at());
        user.setUpdated_at(userDetails.getUpdated_at());

        return userRepository.save(user);
    }

    public User createUser(User user) {
        String pw = passwordEncoder.encode(user.getPassword());

        user.setPassword(pw);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        user.setCreated_at(sqlDate);
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailOrName(email, email).orElseThrow((() ->
                new UsernameNotFoundException("Valeur manquante dans l'Optional")
        ));

    }

}
