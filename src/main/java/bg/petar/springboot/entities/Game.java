package bg.petar.springboot.entities;


import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gameWord;
    private boolean isOver;
    private int numberOfTriesLeft;
    private String progressWord;

    public Game() {
    }

    public Game(String gameWord) {
        this.gameWord = gameWord;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public int getNumberOfTriesLeft() {
        return numberOfTriesLeft;
    }

    public void setNumberOfTriesLeft(int numberOfTriesLeft) {
        this.numberOfTriesLeft = numberOfTriesLeft;
    }

    public Long getId() {
        return this.id;
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

    public String getProgressWord() {
        return progressWord;
    }

    public void setProgressWord(String progressWord) {
        this.progressWord = progressWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id) && gameWord.equals(game.gameWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameWord);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameWord='" + gameWord + '\'' +
                ", isOver=" + isOver +
                ", numberOfTriesLeft=" + numberOfTriesLeft +
                ", progressWord='" + progressWord + '\'' +
                '}';
    }
}
