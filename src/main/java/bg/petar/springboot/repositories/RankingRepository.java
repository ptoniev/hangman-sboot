package bg.petar.springboot.repositories;

import bg.petar.springboot.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Ranking findByPlayerName(String playerName);
}
