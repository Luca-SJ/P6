//package com.openclassrooms.mddapi.Controllers;
//
//import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
//import com.openclassrooms.mddapi.Models.News;
//import com.openclassrooms.mddapi.Models.NewsDTO;
//import com.openclassrooms.mddapi.Models.User;
//import com.openclassrooms.mddapi.Services.NewsService;
//import com.openclassrooms.mddapi.Services.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Controller
//public class NewsRestController {
//
//    @Autowired
//    private NewsService newsService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//
//    @Operation(summary = "Récupère tous les articles", description = "Récupère tous les articles présent dans la BDD")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Article(s) trouvé(e)s"),
//            @ApiResponse(responseCode = "404", description = "Article(s) inconnu(s)")
//    })
//    @GetMapping("/news*")
//    @ResponseBody
//    public List<News> getAllNews() {
//        List<News> news = newsService.getNewsList(created_at, description, title, owner_id, picture, updated_at);
//        return news.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Operation(summary = "Récupère un article", description = "Récupère les infos d'un article en fonction de son ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Article trouvé"),
//            @ApiResponse(responseCode = "404", description = "Article inconnu")
//    })
//    @GetMapping("/news*/{id}")
//    @ResponseBody
//    public NewsDTO getNews(@PathVariable(value = "id") Long NewsID) {
//        return convertToDto(newsService.getNewsById(NewsID));
//    }
//
//    @Operation(summary = "Supprime un article", description = "Supprime un article en fonction de son ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Article supprimé avec succès"),
//            @ApiResponse(responseCode = "404", description = "Article non trouvé")
//    })
//    @DeleteMapping("/news/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteNews(@PathVariable(value = "id") Long NewsID) throws ResourceNotFoundException {
//        newsService.deleteByID(NewsID);
//    }
//
//    @Operation(summary = "Mettre à jour un article", description = "Mettre à jour les informations d'un article en fonction de son ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
//            @ApiResponse(responseCode = "400", description = "Échec de la mise à jour de l'article"),
//            @ApiResponse(responseCode = "404", description = "Article non trouvé")
//    })
//    @PutMapping("/news/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateNews(@PathVariable(value = "id") Long NewsID, @RequestBody NewsDTO newsDTO) {
//        if(!Objects.equals(NewsID, newsDTO.getId())){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        News news = convertToEntity(newsDTO);
//        newsService.updateNews(news);
//    }
//
//    @Operation(summary = "Création d'un article", description = "Permet la création d'un article")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Article créé avec succès"),
//            @ApiResponse(responseCode = "400", description = "Échec de la création de l'article")
//    })
//    @PostMapping("/news*")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public NewsDTO createNews(@RequestBody NewsDTO newsDto) {
//        News newsEntity = convertToEntity(newsDto);
//        News newsCreated = newsService.createNews(newsEntity);
//        return convertToDto(newsCreated);
//    }
//
//    private NewsDTO convertToDto(News news) {
//        NewsDTO newsDTO = modelMapper.map(news, NewsDTO.class);
//        NewsDTO.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
//        return newsDTO;
//    }
//
//    private News convertToEntity(NewsDTO newsDTO) throws ParseException {
//        News news = modelMapper.map(newsDTO, News.class);
//        news.setSubmissionDate(newsDTO.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (newsDTO.getId() != null) {
//            News oldPost = newsService.getPostById(newsDTO.getId());
//            news.setRedditID(oldPost.getRedditID());
//            news.setSent(oldPost.isSent());
//        }
//        return news;
//    }
//}