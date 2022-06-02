package bg.petar.springboot.controllers;

import bg.petar.springboot.entities.Game;

import bg.petar.springboot.model.HangmanInput;
import bg.petar.springboot.repositories.GameRepository;

import bg.petar.springboot.service.HangmanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest/game")
public class RestGameController {


    private final GameRepository gameRepository;

    @Autowired
    HangmanServiceImpl hangmanService;

    @Autowired
    public RestGameController(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    //Get all finished games
    @GetMapping("/all-games")
    public List<Game> findAll() {
//        List<EntityModel<Game>> games = gameRepository.findAll().stream()
//                .map(game -> EntityModel.of(game,
//                        linkTo(methodOn(RestGameController.class).findById(game.getId())).withSelfRel(),
//                        linkTo(methodOn(RestGameController.class).findAll()).withRel("game")))
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(games, linkTo(methodOn(RestGameController.class).findAll()).withSelfRel());
        return gameRepository.findAll();
    }

    // Get specific game information by Id
    @GetMapping("/{gameId}")
    public Game findById(@PathVariable Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(()-> new IllegalArgumentException("Game not found"));
    }

    // Start a new game
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Game> startGame(@RequestBody Game newGame)
    {
        return new ResponseEntity<>(gameRepository.save(newGame), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{gameId}")
    public ResponseEntity<Game> makeTry(@PathVariable Long gameId, @RequestBody HangmanInput hangmanInput, Principal principal) throws IOException {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        String playerName = null;
        if(principal!=null){
            playerName = principal.getName();
        }
        if(optionalGame.isPresent()){
            hangmanService.makeTry(hangmanInput, gameId, playerName);

            return new ResponseEntity<>(optionalGame.get(), HttpStatus.OK);
                    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

}
