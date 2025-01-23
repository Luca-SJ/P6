package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.CommentDTO;
import com.openclassrooms.mddapi.Services.Interfaces.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comment Controller")
public class CommentController {
    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Récupère tous les commentaires d'un article", description = "Récupère tous les commentaires d'un article présent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commentaire(s) trouvé(e)s"),
            @ApiResponse(responseCode = "404", description = "Commentaire(s) inconnu(s)")
    })
    @GetMapping("/article/{id}")
    public List<CommentDTO> getAllComment(@PathVariable(value = "id") Long id) {
        return commentService.findAllByID(id);
    }

    @Operation(summary = "Création d'un commentaires sur un article", description = "Permet la Création d'un commentaires sur un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commentaire publié avec succès"),
            @ApiResponse(responseCode = "400", description = "Échec de la création du commentaire")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@Validated @RequestBody CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
    }
}
