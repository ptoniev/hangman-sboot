package bg.petar.springboot.service;


import bg.petar.springboot.entities.Game;
import bg.petar.springboot.entities.GameStatistic;
import bg.petar.springboot.entities.Ranking;
import bg.petar.springboot.model.HangmanInput;
import bg.petar.springboot.utils.TableEntry;
import bg.petar.springboot.utils.TableEntryWithDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HangmanServiceImpl implements HangmanService {

    @Autowired
    HangmanUtils hangmanUtils;

    @Autowired
    GameService gameService;

    @Autowired
    RankingService rankingService;

    @Autowired
    GameStatisticService gameStatisticService;

    @Autowired
    HttpSession session;

    @Override
    public boolean hasUserWon(Game game) {
        @SuppressWarnings("unchecked")

        String progressWord = game.getProgressWord();
        if (progressWord.contains("_")) {
            return false;
        } else return true;
    }

    @Override
    public String makeTry(HangmanInput hangmanInput, Long gameId, String playerName)
            throws IOException {
        Character inputLetter = hangmanInput.getInput().charAt(0);
        @SuppressWarnings("unchecked")
        Optional<Game> optionalGame = gameService.getById(gameId);
        Game game;
        if (optionalGame.isPresent())
            game = optionalGame.get();
        else
            return "index";

        String gameWord = game.getGameWord();
        int wrongGuesses = (6 - game.getNumberOfTriesLeft());

        if (gameWord.indexOf(Character.toUpperCase(inputLetter)) == -1) {
            wrongGuesses++;
            session.setAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR, wrongGuesses);
            session.setAttribute("picture", drawPicture(wrongGuesses));
        }
        if (wrongGuesses >= 6) {
            game.setOver(true);
            saveGame(game, playerName);
            return "defeatPage";
        }
        game.setGuessedLetters((game.getGuessedLetters() + inputLetter).toUpperCase());
        hangmanUtils.updateCensoredWord(game);
        if (hasUserWon(game)) {
            game.setOver(true);
            saveGame(game, playerName);
            return "victoryPage";
        }
        game.setNumberOfTriesLeft(6 - wrongGuesses);
        saveGame(game, playerName);
        return "gamePage";
    }

    @Override
    public Game startNewGame()
            throws IOException {
        Game game = new Game();
        game.setNumberOfTriesLeft(6);
        game.setGuessedLetters("");
        session.setAttribute("picture", "");
        session.setAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR, (6 - game.getNumberOfTriesLeft()));
        hangmanUtils.initWordAndStore(game);
        hangmanUtils.countLettersInGameWord(game);
        gameService.saveGame(game);

        return game;
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

    @Transactional
    public void saveGame(Game game, String playerName) {
        Game savedGame = gameService.saveGame(game);
        GameStatistic savedGameStatistic = gameStatisticService.updateGameStatisticRankingId(savedGame);
        savedGameStatistic.setPlayerName(playerName);
        Ranking savedRanking = rankingService.updateGameRanking(savedGame, playerName);

    }

    public void saveUsernameToContext(String userName, HttpSession session) {
        session.setAttribute("username", userName);
    }

    public List<TableEntry> getAllRankingsInTableEntries() {
        List<Ranking> rankings = rankingService.findAll();
        return rankings.stream().map((this::createTableEntry)).collect(Collectors.toList());
    }

    private TableEntry createTableEntry(Ranking ranking) {
        TableEntry tableEntry = new TableEntry();
        tableEntry.name = ranking.getPlayerName();
        tableEntry.gamesWon = ranking.getGameStatisticList().size();
        return tableEntry;
    }

    private TableEntryWithDate createTableEntryWithDate(Ranking ranking) {
        TableEntryWithDate tableEntryWithDate = new TableEntryWithDate();
        tableEntryWithDate.name = ranking.getPlayerName();
        tableEntryWithDate.gamesWon = ranking.getGameStatisticList().size();
        tableEntryWithDate.dateAttr = ranking.getDateAttr();
        return tableEntryWithDate;
    }

    public List<TableEntryWithDate> getAllRankingsInTableEntriesWithDate() {
        List<Ranking> rankings = rankingService.findAll();
        return rankings.stream().map((this::createTableEntryWithDate)).collect(Collectors.toList());
    }


}

