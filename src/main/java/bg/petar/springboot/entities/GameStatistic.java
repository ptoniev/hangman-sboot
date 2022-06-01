package bg.petar.springboot.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "game_statistic")
public class GameStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Game game;


    private String playerName;

    public GameStatistic() {
    }

    public GameStatistic(Game game) {
        this.game = game;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
