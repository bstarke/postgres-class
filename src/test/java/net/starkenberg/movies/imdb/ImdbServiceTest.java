package net.starkenberg.movies.imdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.starkenberg.movies.cinema.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImdbServiceTest {

    @Autowired
    ImdbService imdbService;

    @Test
    void imdbService_gets_movie() throws JsonProcessingException {
        Movie movie = imdbService.getImdbMovie("tt1197624");
        assertEquals("Law Abiding Citizen", movie.getTitle());
    }
}