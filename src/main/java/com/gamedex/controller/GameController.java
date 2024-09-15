package com.gamedex.controller;


import com.gamedex.dto.GameSearchRequest;
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
@CrossOrigin(origins = "http://localhost:5173")
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
            @RequestParam(required = false) String operatingSystem,
            @RequestParam(required = false) String date
    )
    {


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
        } else if(date != null){
            return gameService.getGameByDate(date);
        }else {
            return gameService.getGames();
        }
    }

    @GetMapping("retro")
    public List<Game> getRetroGames()
    {
        return gameService.getRetroGames();
    }

    @PostMapping("customfind")
    public ResponseEntity<List<Game>> findGames(@RequestBody GameSearchRequest req)
    {
        List<Game> games = gameService.customFind(
                req.getName(),
                req.getGenre(),
                req.getPlatform(),
                req.getDeveloper()
        );
        return ResponseEntity.ok(games);
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
