package com.gamedex.Repository;

import com.gamedex.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findById(long id);

    @Query("SELECT g FROM Game g WHERE (g.genre LIKE %:genre1% OR g.genre LIKE %:genre2%) " +
            "AND (g.producer LIKE %:producer1% OR g.producer LIKE %:producer2% " +
            "OR g.developer LIKE %:developer1% OR g.developer LIKE %:developer2%)")
    List<Game> findRetro(
            @Param("genre1") String genre1,
            @Param("genre2") String genre2,
            @Param("producer1") String producer1,
            @Param("producer2") String producer2,
            @Param("developer1") String developer1,
            @Param("developer2") String developer2
    );


    @Query("SELECT g FROM Game g WHERE g.name LIKE %:name% AND g.genre LIKE %:genre% AND g.operating_system LIKE %:platform% AND (g.developer LIKE %:developer% OR g.producer LIKE %:developer%)")
    List<Game> customFind(
            @Param("name") String name,
            @Param("genre") String genre,
            @Param("platform") String platform,
            @Param("developer") String developer

    );
}
