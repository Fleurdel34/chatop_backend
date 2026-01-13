package com.example.chatop.security;


import com.example.chatop.pojo.User;
import com.example.chatop.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

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


    @SneakyThrows
    public Map<String, String> generate(String email){
        User user = this.userService.loadUserByUsername(email);
        return this.generateJwt(user);
    }

    public Map<String, String> generateJwt(User user) {

        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30*60*1000;

        Map<String, Object> claims = Map.of(
                "name", user.getName(),
                 Claims.EXPIRATION,new Date(expirationTime),
                Claims.SUBJECT, user.getEmail()
        );

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

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before((new Date()));
    }


    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token)  {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
