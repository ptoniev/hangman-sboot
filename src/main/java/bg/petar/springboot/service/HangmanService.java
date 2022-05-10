package bg.petar.springboot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface HangmanService {
    int getRandomGameId(HttpServletRequest request, HttpSession session);

    boolean hasUserWon(HttpServletRequest request);

    void makeTry(HttpServletRequest request, HttpServletResponse resp, HttpSession session) throws IOException;

    void startNewGame(HttpServletRequest request, HttpSession session) throws IOException;

    String drawPicture(int wrongGuesses);
}
