package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.News;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Services.Interfaces.INewsService;
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
@RequestMapping("/api/news")
@Tag(name = "News Controller")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("")
    public List<News> getAllNews() {
        return newsService.findAll();
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("/user/{id}")
    public List<News> getNewsByUserId(@PathVariable(value = "id") Long id, Principal principal) {
        return newsService.getNewsByUserId(id);
    }

    @Operation(summary = "Récupère un article", description = "Récupère les infos d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article inconnu")
    })
    @GetMapping("/{id}")
    public News getNewsByID(@PathVariable(value = "id") Long NewsID) throws ResourceNotFoundException {
        News news = newsService.findByID(NewsID);

        return news;
    }

    @Operation(summary = "Supprime un article", description = "Supprime un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable(value = "id") Long NewsID) throws ResourceNotFoundException {
        newsService.deleteByID(NewsID);
    }

    @Operation(summary = "Mettre à jour un article", description = "Mettre à jour les informations d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la mise à jour de l'article"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @PutMapping("/{id}")
    public News updateNews(@PathVariable(value = "id") Long NewsID,
                           @Validated @RequestBody News newsDetails)throws ResourceNotFoundException {
        return newsService.updateNewsByID(NewsID, newsDetails);
    }

    @Operation(summary = "Création d'un article", description = "Permet la création d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la création de l'article")
    })
    @PostMapping("")
    public News createNews(@Validated @RequestBody News news) {
        return newsService.createNews(news);
    }
}
