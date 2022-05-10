package bg.petar.springboot.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    @OneToMany(mappedBy = "ranking", cascade = CascadeType.ALL)
    private List<GameStatistic> gameStatisticList;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Ranking() {
    }

    public Ranking(List<GameStatistic> gameStatisticList, String playerName) {
        this.gameStatisticList = gameStatisticList;
        this.playerName = playerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GameStatistic> getGameStatisticList() {
        return gameStatisticList;
    }

    public void setGameStatisticList(List<GameStatistic> gameStatisticList) {
        this.gameStatisticList = gameStatisticList;
    }
}
