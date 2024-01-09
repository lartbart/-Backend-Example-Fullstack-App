package com.example.mdbspringboot.userdata;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userDTO) {

        boolean verification = userService.verifyLogin(userDTO);
        String token = userService.generateToken(userDTO);

        if (verification) {
            return ResponseEntity.ok(token);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }


    @CrossOrigin
    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDTO userDto) {
        userService.Register(userDto);
    }
}
