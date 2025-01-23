package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.RegisterDTO;
import com.openclassrooms.mddapi.Dtos.TokenResponse;
import com.openclassrooms.mddapi.Dtos.LoginDTO;
import com.openclassrooms.mddapi.Services.Interfaces.IJWTService;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Tag(name = "Auth Controller")

public class AuthController {

    private final IUserService userService;

    private final IJWTService jwtService;


    public AuthController(IUserService userService, IJWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Operation(
            summary = "User Registration",
            description = "Allows a new user to register by providing a valid name, email, and password."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered."),
            @ApiResponse(responseCode = "400", description = "Invalid request. Please check the provided input."),
            @ApiResponse(responseCode = "403", description = "Forbidden. The user does not have permission to access this resource."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Something went wrong on the server.")
    })
    @PostMapping ("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse register(@RequestBody @Validated  RegisterDTO user) {

        userService.createUser(user);
        final String token = jwtService.generateToken(user.getEmail());
        return new TokenResponse(token);
    }

    @Operation(summary = "Envoi les données de connexion", description = "Permet à un utilisateur de se connecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la connexion")
    })
    @PostMapping ("/auth/login")
    public TokenResponse login(@RequestBody @Validated LoginDTO user) {

        boolean isAuthenticated = userService.authenticate(user);

        if (isAuthenticated) {
            final String token = jwtService.generateToken(user.getNameOrEmail());
            return new TokenResponse(token);

        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }



}
