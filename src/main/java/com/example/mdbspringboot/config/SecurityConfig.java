package com.example.mdbspringboot.config;

import com.example.mdbspringboot.auth.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtTokenProvider jwtTokenProvider () {
        return  new JwtTokenProvider();
    }
}
