package com.example.chatop.controller;

import com.example.chatop.dto.AuthenticationDTO;
import com.example.chatop.exception.RequestException;
import com.example.chatop.pojo.User;
import com.example.chatop.security.JwtService;
import com.example.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Tag(name = "Users", description = "user management")
@AllArgsConstructor
@RestController
@RequestMapping({"/api/auth"})
public class UserController {

   private final AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;



    @Operation(summary = "register  user")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "register user, create account and return JWTToken"),
    @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        this.userService.register(user);
        return this.jwtService.generateJwt(user);
    }


    @Operation(summary = "login  user")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "login user account and return JWTToken"),
    @ApiResponse(responseCode = "400", description = "Bad Request"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @SneakyThrows
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationDTO authenticationDTO){

        if(authenticationDTO.email() == null || authenticationDTO.password() == null){
            throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION, "Username ou password non renseignés");
        }

        final Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (authenticationDTO.email(), authenticationDTO.password()));

        if(authenticate.isAuthenticated()){
            return this.jwtService.generate(authenticationDTO.email());
        }
        return null;
    }
}
