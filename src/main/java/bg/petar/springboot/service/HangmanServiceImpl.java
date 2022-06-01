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
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpSession session;

    @Override
    public int getRandomGameId(HttpServletRequest request, HttpSession session) {
        Random obj = new Random();
        int randId = obj.nextInt(5000);
        session.setAttribute(HangmanUtils.GAME_ID_ATTR, randId);
        return randId;
    }

    @Override
    public boolean hasUserWon(HttpServletRequest request) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashSet<Character> guessedLetters =
                (HashSet<Character>) session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR);
        String word = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
        for (int i = 1; i < word.length() - 1; i++) {
            if (!guessedLetters.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void makeTry(HangmanInput hangmanInput)
            throws IOException {
        Character inputLetter = hangmanInput.getInput().charAt(0);
        @SuppressWarnings("unchecked")
        HashSet<Character> guessedLetters =
                (HashSet<Character>) session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR);
        String gameWord = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
        int wrongGuesses = (int) session.getAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR);
        String playerName = (String) session.getAttribute("username");
        Long currentId = (Long) session.getAttribute("gameId");

        Optional<Game> optionalGame = gameService.getById(currentId);
        Game game;
        if(optionalGame.isPresent())
        game = optionalGame.get(); else
        return;

        if (gameWord.indexOf(Character.toUpperCase(inputLetter)) == -1) {
            wrongGuesses++;
            session.setAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR, wrongGuesses);
            session.setAttribute("picture", drawPicture(wrongGuesses));
        }
        if (wrongGuesses >= 6) {
            game.setOver(true);
            saveGame(game, playerName);
            response.sendRedirect("defeatPage");

        }
        guessedLetters.add(Character.toUpperCase(inputLetter));
        session.setAttribute(HangmanUtils.GUESSED_LETTERS_ATTR, guessedLetters);
        if (hasUserWon(request)) {
            game.setOver(true);
            saveGame(game, playerName);
        response.sendRedirect("victoryPage");

        }
        hangmanUtils.updateCensoredWord(request);
        game.setNumberOfTriesLeft(6-wrongGuesses);
        game.setProgressWord((String) session.getAttribute("censoredWord"));
        saveGame(game, playerName);
    }

    @Override
    public Game startNewGame(HttpServletRequest request, HttpSession session)
            throws IOException {
        session.setAttribute("picture", "");
        hangmanUtils.initWordAndStore(request);
        hangmanUtils.countLettersInGameWord(request);
       String gameWord = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
        Game game = new Game(gameWord);
        gameService.saveGame(game);
              session.setAttribute("gameId", game.getId());
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

    public void saveUsernameToContext(String userName, HttpSession session)
    {
        session.setAttribute("username", userName);
    }

    public List<TableEntry> getAllRankingsInTableEntries(){
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

    public List<TableEntryWithDate> getAllRankingsInTableEntriesWithDate(){
        List<Ranking> rankings = rankingService.findAll();
        return rankings.stream().map((this::createTableEntryWithDate)).collect(Collectors.toList());
    }


}

