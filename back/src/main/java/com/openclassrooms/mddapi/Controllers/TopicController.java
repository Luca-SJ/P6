package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.Interfaces.ITopicService;
import com.openclassrooms.mddapi.Services.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/topics")
@Tag(name = "Theme Controller")
public class TopicController {

    @Autowired
    private ITopicService topicService;
    @Autowired
    private IUserService userService;

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping()
    public List<Topic> getAllTheme() {
        return topicService.findAll();
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("/user/{id}")
    public List<Topic> getTopicSubscribe(@PathVariable(value = "id") Long id, Principal principal) {
        return topicService.getTopicsByUserId(id);
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })

    @PostMapping("/subscribe/{id}")
    public Subscription subscribe(@PathVariable(value = "id") Long idTheme, Principal principal) throws ResourceNotFoundException {
        User user = userService.findByEmail(principal.getName());

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
