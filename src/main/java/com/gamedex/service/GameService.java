package com.gamedex.service;

import com.gamedex.Repository.GameRepository;
import jakarta.transaction.Transactional;
import com.gamedex.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getSingleGame(long id)
    {
        return gameRepository.findById(id);
    }

    public List<Game> getGames() {
        System.out.println("Executed getGames");
        return gameRepository.findAll();
    }

    public List<Game> getGamesByGenre(String genre)
    {
        return gameRepository.findAll().stream().filter(game -> game.getGenre().toLowerCase().contains(genre)).collect(Collectors.toList());
    }

    public List<Game> getGameByName(String name)
    {
        return gameRepository.findAll().stream().filter(game -> game.getName().toLowerCase().contains(name)).collect(Collectors.toList());
    }

    public List<Game> getGameByDev(String developer)
    {
        return gameRepository.findAll().stream().filter(game -> game.getDeveloper().toLowerCase().contains(developer)).collect(Collectors.toList());
    }

    public List<Game> getGameByProducer(String producer)
    {
        return gameRepository.findAll().stream().filter(game -> game.getProducer().toLowerCase().contains(producer)).collect(Collectors.toList());
    }

    public List<Game> getGameByPlatform(String platform)
    {
        return gameRepository.findAll().stream().filter(game -> game.getOperating_system().toLowerCase().contains(platform)).collect(Collectors.toList());
    }

    public Game addGame(Game game)
    {
        gameRepository.save(game);
        return game;
    }

    public Game updateGame(Game game)
    {
        Optional<Game> existingGame = Optional.ofNullable(gameRepository.findById(game.getId()));

        if(existingGame.isPresent())
        {
            Game gameToUpdate = existingGame.get();
            gameToUpdate.setName(game.getName());
            gameToUpdate.setDeveloper(game.getDeveloper());
            gameToUpdate.setProducer(game.getProducer());
            gameToUpdate.setGenre(game.getGenre());
            gameToUpdate.setDate(game.getDate());
            gameRepository.save(gameToUpdate);
            return gameToUpdate;
        }
        return null;
    }

    @Transactional
    public void deleteGame(long id)
    {
        Optional<Game> gameToDelete = Optional.ofNullable(gameRepository.findById(id));

        if(gameToDelete.isPresent())
        {
            gameRepository.delete(gameToDelete.get());
        }
    }

}
