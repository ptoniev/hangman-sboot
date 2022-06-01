package bg.petar.springboot.repositories;

import bg.petar.springboot.entities.GameStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {

    Optional<GameStatistic> findByGameId(Long gameId);
}
