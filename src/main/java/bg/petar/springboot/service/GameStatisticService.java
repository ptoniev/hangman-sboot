package bg.petar.springboot.service;

import bg.petar.springboot.entities.Game;
import bg.petar.springboot.entities.GameStatistic;
import bg.petar.springboot.entities.Ranking;
import bg.petar.springboot.repositories.GameStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameStatisticService {

    @Autowired
    private GameStatisticRepository gameStatisticRepository;

    public GameStatistic updateGameStatisticRankingId(Game game){
        Optional<GameStatistic> optionalGameStatistic = gameStatisticRepository.findByGameId(game.getId());
        GameStatistic gameStatistic;
        if(optionalGameStatistic.isPresent())
             gameStatistic= optionalGameStatistic.get();
        else gameStatistic = new GameStatistic(game);
        return gameStatisticRepository.save(gameStatistic);
    }
}
