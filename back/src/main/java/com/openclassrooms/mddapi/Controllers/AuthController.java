package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.TokenResponse;
import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.Interfaces.IJWTService;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import com.openclassrooms.mddapi.Services.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Auth Controller")
public class AuthController {
    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    private IUserService userService;
    private IJWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

/*    @PostMapping("/login")
    public String getToken(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return token;
    }*/


    @Operation(summary = "Envoi les données d'inscription", description = "Permet à un utilisateur de s'enregistrer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enregistrement effectué avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de l'enregistrement")
    })
    @PostMapping ("/auth/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String token = jwtService.generateToken(user.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String> tokenMAP = new HashMap<>();
        tokenMAP.put("token", token);

        User newUser = userService.createUser(user);
        // return ResponseEntity.ok(newUser);
        return ResponseEntity.ok(tokenMAP);
    }

    @Operation(summary = "Envoi les données de connexion", description = "Permet à un utilisateur de se connecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la connexion")
    })
    @PostMapping ("/auth/login")
    public TokenResponse login(@RequestBody @Validated UserDTO user) {

            User userDetail = (User) userService.loadUserByUsername(user.getNameOrEmail());
            if (passwordEncoder.matches(user.getPassword(), userDetail.getPassword())) {

                //Authentication authentication = new UsernamePasswordAuthenticationToken(user.getNameOrEmail(), user.getPassword());
                String token = jwtService.generateToken(user.getNameOrEmail());
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getNameOrEmail(), user.getPassword()));
                //SecurityContextHolder.getContext().setAuthentication(authentication);


                    return new TokenResponse(token);

            } else {
                throw new UsernameNotFoundException("User not found");
            }
    }

    @Operation(summary = "Récupère les infos de la personne connectée", description = "Récupère les infos de la personne connectée en fonction de l'ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations récupérées avec succès"),
            @ApiResponse(responseCode = "400", description = "Informations non récupérées")
    })
    @GetMapping ("/auth/me")
    public ResponseEntity<User> getUserByID(Principal user) throws ResourceNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return ResponseEntity.ok(userService.findByEmail(email));
    }

}
