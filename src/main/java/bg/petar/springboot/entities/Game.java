package bg.petar.springboot.entities;


import javax.persistence.*;

@Entity
@Table
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gameWord;
    @OneToOne(cascade = CascadeType.ALL)
    private GameStatistic gameStatistic;

    public Game() {
    }

    public Game(String gameWord, GameStatistic gameStatistic) {
        this.gameWord = gameWord;
        this.gameStatistic = gameStatistic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameWord() {
        return gameWord;
    }

    public void setGameWord(String gameWord) {
        this.gameWord = gameWord;
    }

    public GameStatistic getGameStatistic() {
        return gameStatistic;
    }

    public void setGameStatistic(GameStatistic gameStatistic) {
        this.gameStatistic = gameStatistic;
    }
}
