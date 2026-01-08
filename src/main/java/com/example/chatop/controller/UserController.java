package com.example.chatop.controller;

import com.example.chatop.dto.AuthenticationDTO;
import com.example.chatop.pojo.User;
import com.example.chatop.security.JwtService;
import com.example.chatop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Build class UserController
 * Receive the request and provide the response
 * @property UserService
 * @requests Post regitrer and login an User
 */
@AllArgsConstructor
@RestController
@RequestMapping({"/api/auth"})
public class UserController {

   private final AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;


    @PostMapping("/registrer")
    public void registrer(@RequestBody User user){
        this.userService.registrer(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationDTO authenticationDTO){
        final Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (authenticationDTO.username(), authenticationDTO.password()));
        if(authenticate.isAuthenticated()){
            return this.jwtService.generate(authenticationDTO.username());
        }
        return null;
    }
}
