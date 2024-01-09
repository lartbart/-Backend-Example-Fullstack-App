package com.example.mdbspringboot.userdata;


import com.example.mdbspringboot.auth.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    final BCryptPasswordEncoder passwordEncoder;

    final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public boolean verifyLogin(UserLoginDTO userDTO) {
        List<UserModel> users = userRepository.findAll();
        Optional<UserModel> foundUser = users.stream().filter(user -> user.getUsername().equals(userDTO.username())).findFirst();
        if (foundUser.isPresent()) {
            return foundUser.filter(userModel -> passwordEncoder.matches(userDTO.password(), userModel.getPassword())).isPresent();
        }
        else {
            return false;
        }
    }

    public String generateToken(UserLoginDTO userDTO) {
        return jwtTokenProvider.createToken(userDTO.username());
    }

    public void Register(UserRegisterDTO userDTO) {
        UserModel userModel = new UserModel(userDTO.username(), userDTO.email(), passwordEncoder.encode(userDTO.password()));
        userRepository.insert(userModel);
    }
}
