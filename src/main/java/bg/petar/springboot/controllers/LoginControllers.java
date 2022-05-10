package bg.petar.springboot.controllers;


import bg.petar.springboot.service.HangmanServiceImpl;
import bg.petar.springboot.utils.TableEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverse;

@Controller
public class LoginControllers {

    @Autowired
    HangmanServiceImpl hangmanServiceImpl;

    @RequestMapping("/login")
    public String loadLoginPage(HttpServletRequest request, HttpSession session) {
        System.out.println("First Login mapping");
        return "login";
    }

    @RequestMapping("/logout-success")
    public String loadLogoutPage() {
        return "logout";
    }

    @RequestMapping("/")
    public String loadHomePage(Principal principal, HttpSession session) {

        List<TableEntry> listOfUsers = hangmanServiceImpl.getAllRankingsInTableEntries();
        listOfUsers.sort(Comparator.comparing(tableEntry -> tableEntry.gamesWon));
        reverse(listOfUsers);

        if (principal != null) {
            hangmanServiceImpl.saveUsernameToContext(principal.getName(), session);
        }
        session.setAttribute("listOfUsers", listOfUsers);
        return "index";
    }


}
