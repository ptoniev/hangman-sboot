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
import java.security.Principal;


@Controller
public class GameController {

    @Autowired
    HangmanServiceImpl hangmanGameService;

    @Autowired
    HttpServletRequest request;

    @PostMapping(path = "/game")
    public RedirectView startGame(@ModelAttribute("hangmanInput") HangmanInput hangmanInput, Model model) throws IOException {

        Game game = hangmanGameService.startNewGame();
        return new RedirectView(request.getContextPath() + "/game/" + game.getId());
    }

    @GetMapping(path = "/game/{gameId}")
    public String processGameStart(@PathVariable(required = false) Long gameId, @ModelAttribute("hangmanInput") HangmanInput hangmanInput, Model model) {
        model.addAttribute("gameId", gameId);
        return "gamePage";
    }

    @PostMapping(path = "/game/{gameId}")
    public String playGame(@Valid @ModelAttribute("hangmanInput") HangmanInput hangmanInput, BindingResult br,
                           @PathVariable Long gameId, Principal principal)
            throws IOException {
        if (br.hasErrors()) {
            return "gamePage";
        }
        String playerName = null;
        if (principal != null) {
            playerName = principal.getName();
        }
        return hangmanGameService.makeTry(hangmanInput, gameId, playerName);
    }


    @GetMapping("/game/victoryPage")
    public String loadVictoryPage() {
        return "victoryPage";
    }

    @GetMapping("/game/defeatPage")
    public String loadDefeatPage() {
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
