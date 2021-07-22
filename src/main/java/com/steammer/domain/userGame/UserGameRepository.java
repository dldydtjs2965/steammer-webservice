package com.steammer.domain.userGame;

import com.steammer.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {

    @Query("SELECT ug FROM UserGame ug WHERE ug.user.id = :userId")
    List<UserGame> findAllByUserHaveGame(@Param("userId") Long userId);
}
