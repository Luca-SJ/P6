package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
@Tag(name = "Theme Controller")
@SecurityRequirement(name = "bearerAuth")

public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable(value = "id") Long UserID) throws ResourceNotFoundException {
        UserDTO userr = userService.findById(UserID);

        return userr;
    }

}
