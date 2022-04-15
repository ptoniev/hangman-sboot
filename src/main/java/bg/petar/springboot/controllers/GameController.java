package bg.petar.springboot.controllers;

import bg.petar.springboot.service.HangmanGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    HangmanGameService hangmanGameService;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping(path = "/game")
    //@PreAuthorize("hasAnyRole('USER.name()', 'GUEST.name()')")
    public void startGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.startNewGame(req, session);
        // When the game start button is clicked we want to redirect to specific game Id
        resp.sendRedirect("/game/" + session.getAttribute("gameId"));
            }

    @GetMapping(path = "/game/{gameId}")
    public String processGameStart(HttpSession session, HttpServletRequest req)
    {
        if(req.isUserInRole("ADMIN")){
            return "adminGamePage";
        }
        return "gamePage";
    }

    @PostMapping(path = "/game/{gameId}")
    public String playGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.makeTry(req, resp, session);
        if(req.isUserInRole("ADMIN")){
            return "adminGamePage";
        }
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

    @RequestMapping("/login")
    public String loadLoginPage() {
        return "login";
    }

    @RequestMapping("/logout-success")
    public String loadLogoutPage() {
        return "logout";
    }

    @RequestMapping("/play-as-guest")
    public void playAsGuest(HttpServletRequest req, HttpSession session, HttpServletResponse resp) throws IOException {
        hangmanGameService.startNewGame(req, session);
        resp.sendRedirect("/play-as-guest/" + session.getAttribute("gameId"));
    }

    @GetMapping(path = "/play-as-guest/{gameId}")
    public String processGameStartAsGuest(HttpSession session)
    {
        return "gamePage";
    }

    @PostMapping(path = "/play-as-guest/{gameId}")
    public String playGameAsGuest(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        hangmanGameService.makeTry(req, resp, session);
        return "gamePage";
    }

    @GetMapping("/word-dictionary")
    @ResponseBody
    public String[] getAllWordsForAdmin() {
       return hangmanGameService.getAllWordsForAdmin();
    }

    @GetMapping("/uncensored-word")
    public String getVisibleWordAdminPage() {
        return "adminUncensoredView";
    }
}
