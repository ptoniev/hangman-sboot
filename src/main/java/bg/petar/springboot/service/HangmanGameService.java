package bg.petar.springboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

@Service
public class HangmanGameService implements GameService {

    @Autowired
    HangmanUtils hangmanUtils;

    @Override
    public int getRandomGameId(HttpServletRequest request, HttpSession session) {
        Random obj = new Random();
        int randId = obj.nextInt(50);
        session.setAttribute(HangmanUtils.GAME_ID_ATTR, randId);
        return randId;
    }

    @Override
    public boolean hasUserWon(HttpServletRequest request) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashSet<Character> guessedLetters =
                (HashSet<Character>) session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR);
        String word = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
        for (int i = 1; i < word.length() - 1; i++) {
            if (!guessedLetters.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void makeTry(HttpServletRequest request, HttpServletResponse resp, HttpSession session)
            throws IOException {
        Character inputLetter = request.getParameter("guess").charAt(0);
        @SuppressWarnings("unchecked")
        HashSet<Character> guessedLetters =
                (HashSet<Character>) session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR);
        String gameWord = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
        int wrongGuesses = (int) session.getAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR);
        if (gameWord.indexOf(Character.toUpperCase(inputLetter)) == -1) {
            wrongGuesses++;
            session.setAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR, wrongGuesses);
            session.setAttribute("picture", drawPicture(wrongGuesses));
        }
        if (wrongGuesses >= 6) {
            resp.sendRedirect("defeatPage");
        }
        guessedLetters.add(Character.toUpperCase(inputLetter));
        session.setAttribute(HangmanUtils.GUESSED_LETTERS_ATTR, guessedLetters);
        if (hasUserWon(request)) {

        resp.sendRedirect("victoryPage");

        }

        hangmanUtils.updateCensoredWord(request);
    }

    @Override
    public void startNewGame(HttpServletRequest request, HttpSession session)
            throws IOException {

        session.setAttribute("picture", "");
        getRandomGameId(request, session);
        hangmanUtils.initWordAndStore(request);
        hangmanUtils.countLettersInGameWord(request);

    }

    @Override
    public String drawPicture(int wrongGuesses) {
        switch (wrongGuesses) {
            case 1:
                return "_____<br>" + "|     |<br>" + "|     O<br>" + "|<br>" + "|<br>" + "|";
            case 2:
                return "_____<br>" + "|     |<br>" + "|     O<br>" + "|     |<br>" + "|<br>" + "|";
            case 3:
                return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|<br>" + "|<br>" + "|";
            case 4:
                return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|\\<br>" + "|<br>" + "|";
            case 5:
                return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|\\<br>" + "|    /<br>" + "|";
            case 6:
                return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|\\<br>" + "|    / \\<br>"
                        + "|";
            default:
                return "  ";
        }

    }

    public String[] getAllWordsForAdmin() {
        return hangmanUtils.getAllWords();
    }
}

