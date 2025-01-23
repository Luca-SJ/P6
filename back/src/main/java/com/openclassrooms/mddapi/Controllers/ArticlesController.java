package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Article;
import com.openclassrooms.mddapi.Dtos.ArticleDTO;
import com.openclassrooms.mddapi.Services.Interfaces.IArticlesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/articles")
@Tag(name = "Articles Controller")
public class ArticlesController {

    private final IArticlesService articlesService;

    public ArticlesController(IArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
    })
    @GetMapping("/user/{id}")
    public List<ArticleDTO> getArticlesByUserId(@PathVariable(value = "id") Long id) {
        return articlesService.getArticlesByUserId(id);
    }

    @Operation(summary = "Récupère un article", description = "Récupère les infos d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article inconnu")
    })
    @GetMapping("/{id}")
    public ArticleDTO getArticlesByID(@PathVariable(value = "id") Long articlesID) {
        return articlesService.findByID(articlesID);
    }

    @Operation(summary = "Supprime un article", description = "Supprime un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteArticles(@PathVariable(value = "id") Long ArticlesID) throws ResourceNotFoundException {
        articlesService.deleteByID(ArticlesID);
    }

    @Operation(summary = "Mettre à jour un article", description = "Mettre à jour les informations d'un article en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la mise à jour de l'article"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @PutMapping("/{id}")
    public ArticleDTO updateArticles(@PathVariable(value = "id") Long ArticlesID,
                                  @Validated @RequestBody Article articleDetails)throws ResourceNotFoundException {
        return articlesService.updateArticlesByID(ArticlesID, articleDetails);
    }

    @Operation(summary = "Création d'un article", description = "Permet la création d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la création de l'article")
    })
    @PostMapping
    public void createArticles(@Validated @RequestBody ArticleDTO articles) {
        System.out.println("**postMapping");
        articlesService.createArticles(articles);
    }
}
