package bg.petar.springboot.controllers;

import bg.petar.springboot.entities.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestGameControllerTest {

    TestRestTemplate restTemplate;

    @BeforeEach
    void init() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    void findOngoing() {
        ResponseEntity<Game> response = restTemplate.getForEntity("http://localhost:8080/rest/game/ongoing", Game.class);
        assertEquals(13, 13);
    }

    @Test
    void findAll() {

    }

    @Test
    void findById() {
    }


    @Test
    void startGame() {
    }

    @Test
    void makeTry() {
    }
}