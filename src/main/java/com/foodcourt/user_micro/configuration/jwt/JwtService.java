package com.foodcourt.user_micro.configuration.jwt;

import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.ITokenPersistencePort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService implements ITokenPersistencePort {

    private String SECRET_KEY = "udRQyx10PcEv0N2nXlKqlgie/91q/KDxUwHGmV/ylNc=";

    @Override //con esto queda implementado el metodo
    public String getToken(User user) { //esos datos llegan del usuario
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getRole());
        claims.put("id", user.getId());

        return generateToken(claims, user);
    }

    private String generateToken(Map<String, ?> claims, User user) { //el "?" es para que traiga cualquier cosa

        return Jwts
                .builder() //esto significa que se va a construir el token
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis())) //milisegundos momento en que se creo el token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30 )) //para que dure un mes
                .signWith( getKey(), SignatureAlgorithm.HS256) //como se construye el metodo?
                .compact();
    }

    private Key getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); //esto es para que se pueda encriptar
    }
}
