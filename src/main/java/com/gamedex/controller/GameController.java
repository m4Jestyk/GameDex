package com.gamedex.controller;


import com.gamedex.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamedex.service.GameService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService)
    {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGame(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String developer,
            @RequestParam(required = false) String producer,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String operatingSystem
    )
    {

        System.out.println("Trying to find games....");

        if(name != null)
        {
            return gameService.getGameByName(name);
        } else if(developer != null)
        {
            return gameService.getGameByDev(developer);
        } else if(producer != null)
        {
            return gameService.getGameByProducer(producer);
        } else if(genre != null)
        {
            return gameService.getGamesByGenre(genre);
        } else if(operatingSystem != null)
        {
            return gameService.getGameByPlatform(operatingSystem);
        } else {
            return gameService.getGames();
        }
    }

    @PutMapping
    public ResponseEntity<Game> updateGame(@RequestBody Game updatedGame)
    {
        Game resultingGame = gameService.updateGame(updatedGame);
        if(resultingGame != null)
        {
            return new ResponseEntity<>(resultingGame, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable long id)
    {
        Game game = gameService.getSingleGame(id);
        gameService.deleteGame(id);
        return ResponseEntity.ok(game);
    }


}
