package bg.petar.springboot.service;

import bg.petar.springboot.entities.GameStatistic;
import bg.petar.springboot.entities.Ranking;
import bg.petar.springboot.repositories.GameStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticService {

    @Autowired
    private GameStatisticRepository gameStatisticRepository;

    public GameStatistic updateGameStatisticRankingId(GameStatistic gameStatistic, Ranking ranking){
        gameStatistic.setRanking(ranking);
        return gameStatisticRepository.save(gameStatistic);
    }
}
