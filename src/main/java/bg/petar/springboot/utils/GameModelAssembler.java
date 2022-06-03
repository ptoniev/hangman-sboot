package bg.petar.springboot.utils;

import bg.petar.springboot.controllers.RestGameController;
import bg.petar.springboot.entities.Game;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class GameModelAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {

    @Override
    public EntityModel<Game> toModel(Game game) {

        return EntityModel.of(game, //
                linkTo(methodOn(RestGameController.class).findById(game.getId())).withSelfRel(),
                linkTo(methodOn(RestGameController.class).findAll()).withRel("games"));
    }
}
