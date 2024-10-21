package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Theme;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.ThemeService;
import com.openclassrooms.mddapi.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Tag(name = "Theme Controller")
public class ThemeController {

    @Autowired
    private ThemeService themeService;
    private UserService userService;

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("/theme*")
    public List<Theme> getAllTheme() {
        return themeService.findAll();
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })

    @PostMapping("/themes/subscribe/{id}")
    public Subscription subscribe(@PathVariable(value = "id") Long ThemeID) throws ResourceNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userService.findByEmail(email);

        return themeService.subscribe(user.getId(), ThemeID);
    }

    @Operation(summary = "Récupère un article", description = "Récupère les infos d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article inconnu")
    })
    @GetMapping("/theme*/{id}")
    public Theme getThemeByID(@PathVariable(value = "id") Long ThemeID) throws ResourceNotFoundException {
        Theme theme = themeService.findByID(ThemeID);

        return theme;
    }

    @Operation(summary = "Supprime un article", description = "Supprime un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @DeleteMapping("/theme/{id}")
    public void deleteTheme(@PathVariable(value = "id") Long ThemeID) throws ResourceNotFoundException {
        themeService.deleteByID(ThemeID);
    }

    @Operation(summary = "Mettre à jour un article", description = "Mettre à jour les informations d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la mise à jour de l'article"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @PutMapping("/theme/{id}")
    public Theme updateTheme(@PathVariable(value = "id") Long ThemeID,
                           @Validated @RequestBody Theme themeDetails)throws ResourceNotFoundException {
        return themeService.updateThemeByID(ThemeID, themeDetails);
    }

    @Operation(summary = "Création d'un article", description = "Permet la création d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la création de l'article")
    })
    @PostMapping("/theme*")
    public Theme createTheme(@Validated @RequestBody Theme theme) {
        return themeService.createTheme(theme);
    }
}
