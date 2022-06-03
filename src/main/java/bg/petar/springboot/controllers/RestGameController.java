package bg.petar.springboot.controllers;

import bg.petar.springboot.entities.Game;

import bg.petar.springboot.model.HangmanInput;
import bg.petar.springboot.repositories.GameRepository;

import bg.petar.springboot.service.HangmanServiceImpl;
import bg.petar.springboot.utils.GameModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/rest/game")
public class RestGameController {

    private final GameRepository gameRepository;
    private final GameModelAssembler gameModelAssembler;

    @Autowired
    HangmanServiceImpl hangmanService;

    @Autowired
    public RestGameController(GameRepository gameRepository, GameModelAssembler gameModelAssembler) {
        this.gameRepository = gameRepository;
        this.gameModelAssembler = gameModelAssembler;
    }

    // Get all games
    @GetMapping("/all-games")
    public CollectionModel<EntityModel<Game>> findAll() {
        List<EntityModel<Game>> games = gameRepository.findAll().stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(games, linkTo(methodOn(RestGameController.class).findAll()).withSelfRel());
    }

    // Get specific game information by specifying game id
    @GetMapping("/{gameId}")
    public EntityModel<Game> findById(@PathVariable Long gameId) {
        Game game = gameRepository.findById(gameId) //
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        return gameModelAssembler.toModel(game);
    }

    // Get all ongoing games
    @GetMapping("/ongoing")
    public CollectionModel<EntityModel<Game>> findOngoing() {
        List<EntityModel<Game>> games = gameRepository.getOngoingGames().stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(games, linkTo(methodOn(RestGameController.class).findOngoing()).withSelfRel());
    }

    // Start a new game
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> startGame() throws IOException {
        EntityModel<Game> entityModel = gameModelAssembler.toModel(gameRepository.save(hangmanService.startNewGame()));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{gameId}")
    public ResponseEntity<?> makeTry(@PathVariable Long gameId, @RequestBody HangmanInput hangmanInput, Principal principal) throws IOException {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        String playerName = null;
        if (principal != null) {
            playerName = principal.getName();
        }
        if (optionalGame.isPresent()) {
            hangmanService.makeTry(hangmanInput, gameId, playerName);
            EntityModel<Game> entityModel = gameModelAssembler.toModel(optionalGame.get());
            return ResponseEntity //
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(entityModel);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
