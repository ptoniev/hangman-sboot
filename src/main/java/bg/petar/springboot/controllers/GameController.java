package bg.petar.springboot.controllers;

import bg.petar.springboot.entities.Game;
import bg.petar.springboot.model.HangmanInput;
import bg.petar.springboot.service.HangmanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


@Controller
public class GameController {

    @Autowired
    HangmanServiceImpl hangmanGameService;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @PostMapping(path = "/game")
    public RedirectView startGame(@ModelAttribute("hangmanInput") HangmanInput hangmanInput) throws IOException {

        Game game = hangmanGameService.startNewGame(request, session);
        System.out.println(game);
        System.out.println(session.getAttribute("gameId"));
        // When the game start button is clicked we want to redirect to specific game Id
        //response.sendRedirect("/game/" + session.getAttribute("gameId"));
        return new RedirectView(request.getContextPath()+ "/game/" + game.getId());
            }

    @GetMapping(path = "/game/{gameId}")
    public String processGameStart(@ModelAttribute("hangmanInput") HangmanInput hangmanInput)
    {
        return "gamePage";
    }

    @PostMapping(path = "/game/{gameId}")
    public String playGame(@Valid @ModelAttribute("hangmanInput") HangmanInput hangmanInput, BindingResult br)
            throws IOException {
        if(br.hasErrors()){
            return "gamePage";
        }
        hangmanGameService.makeTry(hangmanInput);
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
