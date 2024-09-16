package com.gamedex.service;

import com.gamedex.Repository.GameRepository;
import com.gamedex.model.Admin;
import jakarta.transaction.Transactional;
import com.gamedex.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
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

    public List<Game> getGameByDate(String date)
    {
        return gameRepository.findAll().stream().filter(game -> game.getDate().toLowerCase().contains(date)).collect(Collectors.toList());
    }

    public List<Game> getRetroGames()
    {
        return gameRepository.findRetro(
                "arcade", "platform",
                "atari", "nintendo",
                "sega", "namco"
        );
    }

    public List<Game> customFind(String name, String genre, String platform, String developer)
    {
        return gameRepository.customFind(name, genre, platform, developer);
    }

    public Game addGame(Game game)
    {
        gameRepository.save(game);
        return game;
    }

    public boolean auth(Admin req) {
//        String hashedId = passwordEncoder.encode(req.getUserId());
//        System.out.println(hashedId);
//        String hashedPass = passwordEncoder.encode(req.getPassword());
//        String hashedEnvId = passwordEncoder.encode(req.getEnvId());
//        System.out.println(hashedEnvId);
//        String hashedEnvPw = passwordEncoder.encode(req.getEnvPw());
//
//        return hashedId.equals(hashedEnvId) && hashedPass.equals(hashedEnvPw);
        return req.getUserId().equals(req.getEnvId()) && req.getPassword().equals(req.getEnvPw());
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
