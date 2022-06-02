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
import java.security.Principal;

public interface HangmanService {

    boolean hasUserWon(Game game);

    String makeTry(HangmanInput hangmanInput, Long gameId, String playerName)
            throws IOException;

    Game startNewGame() throws IOException;

    String drawPicture(int wrongGuesses);
}
