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
        } else {
            return false;
        }
    }

    public String generateToken(UserLoginDTO userDTO) {
        return jwtTokenProvider.createToken(userDTO.username());
    }

    public ResponseEntity<String> Register(UserRegisterDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        } else {
            UserModel userModel = new UserModel(userDTO.username(), userDTO.email(), passwordEncoder.encode(userDTO.password()));
            userRepository.insert(userModel);
            return ResponseEntity.ok("");
        }
    }

    public void UpdateData(String username, UserUpdateDTO data) {
        UserModel user = userRepository.findByUsername(username);

        if (data.username() != null && !userRepository.existsByUsername(data.username())) {
            user.setUsername(data.username());
        }
        if (userRepository.existsByUsername(data.username())) {
            throw new Error();
        }
        if (data.password() != null) {
            user.setPassword(passwordEncoder.encode(data.password()));
        }
        if (data.email() != null) {
            user.setEmail(data.email());
        }
        userRepository.save(user);
    }


    public void DeleteData(String username) {
        UserModel user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}

