package bg.petar.springboot.service;

import bg.petar.springboot.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Component
public class HangmanUtils {
    protected static final String GAME_WORD_ATTR = "gameWord";
    protected static final String WRONG_GUESS_NUMBER_ATTR = "wrongGuessNumber";
    protected static final String GUESSED_LETTERS_ATTR = "guessedLetters";
    protected static final String GAME_ID_ATTR = "gameId";

    @Autowired
    HttpServletRequest request;

    public HangmanUtils() {
    }

    protected String getRandomWord() {
        String[] words = {"Bmw", "Audi", "Mercedes", "Bentley", "Ferrari", "Opel", "Maseratti", "Fiat",
                "Hyundai", "Volkswagen", "Tesla"};
        Random obj = new Random();
        int randNum = obj.nextInt(11);
        String word = (words[randNum]).toUpperCase();
        return word;
    }

    public String[] getAllWords() {
        String[] words = {"Bmw", "Audi", "Mercedes", "Bentley", "Ferrari", "Opel", "Maseratti", "Fiat",
                "Hyundai", "Volkswagen", "Tesla"};
        return words;
    }

    protected void initWordAndStore(Game game) {
        // HttpSession session = request.getSession();
        String gameWord = getRandomWord();
        game.setGameWord(gameWord);
        //   game.setGuessedLetters(new HashSet<Character>());
//        session.setAttribute(GAME_WORD_ATTR, gameWord);
//        session.setAttribute(WRONG_GUESS_NUMBER_ATTR, 0);
//        session.setAttribute(GUESSED_LETTERS_ATTR, new HashSet<Character>());
        updateCensoredWord(game);

    }

    protected void updateCensoredWord(Game game) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
//        HashSet<Character> guessedLetters =
//                (HashSet<Character>) game.getGuessedLetters();
        String word = game.getGameWord();
        String wordToReturn = word;
        for (int i = 1; i < word.length() - 1; i++) {
            if (game.getGuessedLetters().indexOf(word.charAt(i)) < 0) {
                wordToReturn = wordToReturn.replace(word.charAt(i), '_');
            }
        }
        game.setProgressWord(wordToReturn);
        session.setAttribute("censoredWord", wordToReturn);
    }

    protected void countLettersInGameWord(Game game) {
        HttpSession session = request.getSession();
        String gameWord = game.getGameWord();
        int lettersNumber = gameWord.length();
        session.setAttribute("lettersNumber", lettersNumber);
    }


}
