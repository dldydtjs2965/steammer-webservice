package com.steammer.config.auth;

import com.steammer.domain.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// 스프링 시큐리티 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable().disable()
                .authorizeRequests() //URL 별 권한 관리 옵션
                .antMatchers("/","/css/**","/images/**","/js/**","/api/**").permitAll() // 모든 사용자
                .antMatchers("/api2/**").hasRole(Role.USER.name()) // 유저 들만
                .anyRequest().authenticated() //설정값 이외의 URL
        .and()
                .logout()
                    .logoutSuccessUrl("/") //로그아웃 성공시 홈으로 이동
                    .invalidateHttpSession(true)
        .and()
                .oauth2Login() // OAuth 2 로그인 기능 설정
                    .userInfoEndpoint()// 로그인 성공이후 사용자 정보를 가져올때의 설정
                        .userService(customOAuth2UserService);// 로그인 성공시 후속 조치할 userService 구현체 등록

    }
}
