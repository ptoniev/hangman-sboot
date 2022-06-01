package bg.petar.springboot.service;

import bg.petar.springboot.entities.Game;
import bg.petar.springboot.model.HangmanInput;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

public interface HangmanService {
    int getRandomGameId(HttpServletRequest request, HttpSession session);

    boolean hasUserWon(HttpServletRequest request);

    void makeTry(HangmanInput hangmanInput)
            throws IOException;

    Game startNewGame(HttpServletRequest request, HttpSession session) throws IOException;

    String drawPicture(int wrongGuesses);
}
