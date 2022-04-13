package bg.petar.springboot.controllers;

import bg.petar.springboot.service.HangmanGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class GameController {
    private static final long serialVersionUID = 1L;

    @Autowired
    HangmanGameService hangmanGameService;

    @PostMapping(path = "/game")
    public void startGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.startNewGame(req, session);
        // When the game start button is clicked we want to redirect to specific game Id
        resp.sendRedirect("/game/" + session.getAttribute("gameId"));
            }

    @GetMapping(path = "/game/{gameId}")
    public String processGameStart(HttpSession session)
    {
        return "gamePage";
    }

    @PostMapping(path = "/game/{gameId}")
    public String playGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.makeTry(req, resp, session);
        return "gamePage";
    }


    @GetMapping("/game/victoryPage")
    public String getVictoryPage(){
        return "victoryPage";
    }
    @GetMapping("/game/defeatPage")
    public String getDefeatPage(){
        return "defeatPage";
    }

//    @GetMapping("/login.jsp")
//    public String getLoginPage() {
//        return "login";
//    }

}
