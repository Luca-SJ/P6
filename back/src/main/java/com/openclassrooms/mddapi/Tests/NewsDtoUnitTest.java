//package com.openclassrooms.mddapi.Tests;
//
//import com.openclassrooms.mddapi.Models.News;
//import com.openclassrooms.mddapi.Models.NewsDTO;
//import org.modelmapper.ModelMapper;
//
//import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
//
//public class NewsDtoUnitTest {
//    private ModelMapper modelMapper = new ModelMapper();
//
//    @Test
//    public void whenConvertNewsEntityToNewsDTO_thenCorrect() {
//        News news = new News();
//        news.setId(1L);
//        news.setTitle(randomAlphabetic(6));
//        news.setUrl("www.test.com");
//
//        NewsDTO newsDTO = modelMapper.map(news, NewsDTO.class);
//        assertEquals(news.getId(), newsDTO.getId());
//        assertEquals(news.getTitle(), newsDTO.getTitle());
//        assertEquals(news.getUrl(), newsDTO.getUrl());
//    }
//
//    @Test
//    public void whenConvertNewsDTOToNewsEntity_thenCorrect() {
//        NewsDTO newsDTO = new NewsDTO();
//        newsDTO.setId(1L);
//        newsDTO.setTitle(randomAlphabetic(6));
//        newsDTO.setUrl("www.test.com");
//
//        News news = modelMapper.map(newsDTO, News.class);
//        assertEquals(newsDTO.getId(), news.getId());
//        assertEquals(newsDTO.getTitle(), news.getTitle());
//        assertEquals(newsDTO.getUrl(), news.getUrl());
//    }
//}
