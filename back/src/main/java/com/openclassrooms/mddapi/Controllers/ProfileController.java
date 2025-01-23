package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.MessageDTO;
import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/me")
@Tag(name = "Users", description = "Endpoints for managing user information")
public class ProfileController {
    private final IUserService userService;

    public ProfileController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Récupère les infos de la personne connectée", description = "Récupère les infos de la personne connectée en fonction de l'ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations récupérées avec succès"),
            @ApiResponse(responseCode = "400", description = "Informations non récupérées")
    })
    @GetMapping
    public UserDTO getUserInfo(Principal user) throws ResourceNotFoundException {
        return userService.findByEmailOrName(user.getName());
    }

    @Operation(summary = "Mettre à jour le pseudo de la personne connectée", description = "Mettre à jour le pseudo de la personne connectée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Données mises à jours avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur dans la mises à jour des données")
    })
    @PutMapping()
    public MessageDTO updateUserInfo(@RequestBody UserDTO userDTO, Principal user) throws ResourceNotFoundException {
        return userService.updateUser(userDTO, user.getName());
    }
}