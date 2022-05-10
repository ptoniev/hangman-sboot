package bg.petar.springboot.entities;

import javax.persistence.*;

@Entity
@Table(name = "game_statistic")
public class GameStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private boolean hasWon;
    private Integer numberOfTriesLeft;

    @OneToOne
    private Game game;

    @ManyToOne
    @JoinColumn(name="ranking_id")
    private Ranking ranking;

    public GameStatistic() {
    }

    public GameStatistic( boolean hasWon, Integer numberOfTriesLeft) {

        this.hasWon = hasWon;
        this.numberOfTriesLeft = numberOfTriesLeft;

    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public Integer getNumberOfTriesLeft() {
        return numberOfTriesLeft;
    }

    public void setNumberOfTriesLeft(Integer numberOfTriesLeft) {
        this.numberOfTriesLeft = numberOfTriesLeft;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
