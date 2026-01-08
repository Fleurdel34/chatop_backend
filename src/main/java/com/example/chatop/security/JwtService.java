package com.example.chatop.security;

import com.example.chatop.pojo.User;
import com.example.chatop.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Create class Service
 * use property UserService
 * @method to generate token with Map
 * @params User
 */

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private  final UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }



    public Map<String, String> generate(String username){
        User user = this.userService.loadUserByUsername(username);
        return this.generateJwt(user);
    }

    private Map<String, String> generateJwt(User user) {

        Map<String, String> claims = Map.of(
                "name", user.getName(),
                "username", user.getUsername()
        );

        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30*60*1000;
        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer", bearer);
    }

    private Key getKey(){
        final byte[] decoder = Decoders.BASE64
                .decode(secretKey);
        return Keys.hmacShaKeyFor(decoder);
    }
}
