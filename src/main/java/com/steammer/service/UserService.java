package com.steammer.service;

import com.steammer.config.auth.dto.SessionUser;
import com.steammer.domain.User.User;
import com.steammer.domain.User.UserRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.web.dto.UserGameSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserGameRepository userGameRepository;

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    @Transactional
    public Long save(UserGameSaveRequestDto requestDto){
        return userGameRepository.save(requestDto.toEntity()).getUserGameKey();
    }

    @Transactional
    public void cancelGame(Long userId, Long gameId){
        userGameRepository.deleteById(gameId+userId);
    }

    @Transactional
    public User findByUser(Long id){
        Optional<User> user =  userRepository.findById(id);
        if(user == null){
            throw new UsernameNotFoundException(id.toString());
        }else{
            return user.get();
        }
    }

    @Transactional
    public Long findByUserId(String email){
        Optional<User> user =  userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }else{
            return user.get().getId();
        }
    }
}
