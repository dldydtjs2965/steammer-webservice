package com.steammer.domain.userGame;

import com.steammer.domain.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {

    @Query("SELECT ug FROM UserGame ug WHERE ug.user.id = :userId")
    List<UserGame> findAllByUserGame(@Param("userId") Long userId);

    @Query("SELECT ug.game FROM UserGame ug WHERE ug.user.id = :userId")
    List<Game> findAllByUserWishGame(@Param("userId") Long userId);
}
