package bg.petar.springboot.repositories;

import bg.petar.springboot.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "select * from game " + "where is_over = false", nativeQuery = true)
    List<Game> getOngoingGames();
}
