package bg.petar.springboot.repositories;

import bg.petar.springboot.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> games = new ArrayList<>();

    public default Game create(Game game){
        games.add(game);
        return game;
    }


}
