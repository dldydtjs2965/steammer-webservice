package com.steammer.service;

import com.steammer.domain.user.User;
import com.steammer.domain.user.UserRepository;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.web.dto.LimitTagGameResponseDto;
import com.steammer.web.dto.UserGameIdResponseDto;
import com.steammer.web.dto.UserGameSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserGameRepository userGameRepository;

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    //게임 북마크 저장
    @Transactional
    public Long save(UserGameSaveRequestDto requestDto){
        return userGameRepository.save(requestDto.toEntity()).getUserGameKey();
    }
    //게임 북마크 취소
    @Transactional
    public void cancelGame(Long userId, Long gameId){
        userGameRepository.deleteById(gameId+userId);
    }

    //유저 검색후 return
    @Transactional
    public User findByUser(Long id){
        Optional<User> user =  userRepository.findById(id);
        if(user == null){
            throw new UsernameNotFoundException(id.toString());
        }else{
            return user.get();
        }
    }

    //email 받아서 유저 id return
    @Transactional
    public Long findByUserId(String email){
        Optional<User> user =  userRepository.findByEmail(email);
        if(user == null && emailCheck(email)){
            throw new UsernameNotFoundException(email);
        }else{
            return user.get().getId();
        }
    }

    //User가 북마크한 게임 id들 return
    @Transactional
    public List<UserGameIdResponseDto> findUserGameId(Long id){
        return userGameRepository.findAllByUserGame(id).stream()
                .map(UserGameIdResponseDto::new)
                .collect(Collectors.toList());
    }

    //유저가 북마크한 게임 리스트 return
    @Transactional
    public List<LimitTagGameResponseDto> findUserWishGame(Long id){
        return userGameRepository.findAllByUserWishGame(id).stream()
                .map(LimitTagGameResponseDto::new)
                .collect(Collectors.toList());
    }

    private boolean emailCheck(String email){
        String EmailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        if(Pattern.matches(EmailPattern, email)) return true;
        return false;
    }
}
