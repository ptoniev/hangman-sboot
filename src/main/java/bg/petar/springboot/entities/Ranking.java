package bg.petar.springboot.entities;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CurrentTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    @OneToMany
    @JoinColumn(name = "ranking_id")
    @JsonIgnore
    private List<GameStatistic> gameStatisticList;
    private Date dateAttr = new Date(System.currentTimeMillis());
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

    public Date getDateAttr() {
        return dateAttr;
    }

    public void setDateAttr(Date dateAttr) {
        this.dateAttr = dateAttr;
    }
}
