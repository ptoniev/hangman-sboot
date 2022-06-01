package bg.petar.springboot.service;

import bg.petar.springboot.entities.Game;
import bg.petar.springboot.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;


    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Optional<Game> getById(Long id) {
        return gameRepository.findById(id);
    }
}
