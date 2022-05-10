package bg.petar.springboot.controllers;

import bg.petar.springboot.service.HangmanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class GameController {
    private static final long serialVersionUID = 1L;

    @Autowired
    HangmanServiceImpl hangmanGameService;

    @PostMapping(path = "/game")
    public void startGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.startNewGame(req, session);
        // When the game start button is clicked we want to redirect to specific game Id
        resp.sendRedirect("/game/" + session.getAttribute("gameId"));
            }

    @GetMapping(path = "/game/{gameId}")
    public String processGameStart(HttpSession session, HttpServletRequest req)
    {
        return "gamePage";
    }

    @PostMapping(path = "/game/{gameId}")
    public String playGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.makeTry(req, resp, session);
        return "gamePage";
    }


    @GetMapping("/game/victoryPage")
    public String loadVictoryPage(){
        return "victoryPage";
    }
    @GetMapping("/game/defeatPage")
    public String loadDefeatPage(){
        return "defeatPage";
    }

    @GetMapping("/word-dictionary")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String[] getAllWordsForAdmin() {
       return hangmanGameService.getAllWordsForAdmin();
    }

    @GetMapping("/uncensored-word")
    @PreAuthorize("hasRole('ADMIN')")
    public String getVisibleWordAdminPage() {
        return "adminUncensoredView";
    }
}
