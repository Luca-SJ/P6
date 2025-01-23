package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.TopicDTO;
import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Services.Interfaces.ITopicService;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/topics")
@Tag(name = "Theme Controller")
@SecurityRequirement(name = "bearerAuth")

public class TopicController {
    private final ITopicService topicService;

    private final IUserService userService;

    public TopicController(ITopicService topicService, IUserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @Operation(summary = "Récupère tous les topics", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping()
    public List<TopicDTO> getAllTopic() {
        return topicService.findAll();
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("/user/{id}")
    public List<TopicDTO> getTopicSubscribe(@PathVariable(value = "id") Long id, Principal principal) {
        return topicService.getTopicsByUserId(id);
    }

    @Operation(
            summary = "Subscribe to a theme",
            description = "Allows the authenticated user to subscribe to a theme by providing its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully."),
            @ApiResponse(responseCode = "404", description = "Theme not found. The provided ID does not match any theme in the database."),
            @ApiResponse(responseCode = "403", description = "Forbidden. The user is not authorized to perform this operation."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. An unexpected error occurred.")
    })
    @PostMapping("/subscribe/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Subscription subscribe(@PathVariable(value = "id") Long idTheme, Principal principal) throws ResourceNotFoundException {
        UserDTO user = userService.findByEmailOrName(principal.getName());
        return topicService.subscribe(user.getId(), idTheme);
    }

/*    @Operation(summary = "Récupère un article", description = "Récupère les infos d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article inconnu")
    })
    @GetMapping("/{id}")
    public Topic getThemeByID(@PathVariable(value = "id") Long ThemeID) throws ResourceNotFoundException {
        Topic theme = topicService.findByID(ThemeID);

        return theme;
    }*/

    @Operation(summary = "Supprime un topic", description = "Supprime un topic en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @DeleteMapping("subscribe/{id}")
    public void deleteTheme(@PathVariable(value = "id") Long ThemeID) throws ResourceNotFoundException {
        topicService.unsubscribeByTopicId(ThemeID);
    }

/*    @Operation(summary = "Mettre à jour un article", description = "Mettre à jour les informations d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la mise à jour de l'article"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @PutMapping("/{id}")
    public Topic updateTheme(@PathVariable(value = "id") Long ThemeID,
                             @Validated @RequestBody Topic themeDetails)throws ResourceNotFoundException {
        return topicService.updateThemeByID(ThemeID, themeDetails);
    }*/

    /*@Operation(summary = "Création d'un article", description = "Permet la création d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la création de l'article")
    })
    @PostMapping()
    public Topic createTheme(@Validated @RequestBody Topic theme) {
        return topicService.createTheme(theme);
    }*/
}
