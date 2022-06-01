package bg.petar.springboot.service;

import bg.petar.springboot.entities.Game;
import bg.petar.springboot.entities.Ranking;
import bg.petar.springboot.repositories.GameStatisticRepository;
import bg.petar.springboot.repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RankingService {
    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private GameStatisticRepository gameStatisticRepository;

    @Autowired
    private GameService gameService;

    public Ranking updateGameRanking(Game game, String playerName) {

        Ranking ranking = rankingRepository.findByPlayerName(playerName);
        if(ranking == null)
        {
            ranking = new Ranking(new ArrayList<>(), playerName);
        }
        //todo gamestatisticsservice
        ranking.getGameStatisticList().add(gameStatisticRepository.findByGameId(game.getId()).get());
        return rankingRepository.save(ranking);
    }


    public List<Ranking> findAll() {
        return rankingRepository.findAll();
    }
}
