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
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userDto) {
        return userService.Register(userDto);
    }

    @CrossOrigin
    @PatchMapping("/update/{username}")
    public ResponseEntity<String> updateUserData(@PathVariable String username, @RequestBody UserUpdateDTO data) {
        userService.UpdateData(username, data);
        return ResponseEntity.ok("");
    }

    @CrossOrigin
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUserData(@PathVariable String username) {
        userService.DeleteData(username);
        return ResponseEntity.ok("");
    }
}
