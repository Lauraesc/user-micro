package com.foodcourt.user_micro.configuration.jwt;

import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.ITokenPersistencePort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService implements ITokenPersistencePort {

    private String SECRET_KEY = "20F0AA9AADA0FB96C93EEB251E715BDAB305098774217B4DA8FA9DD4F65B8518";

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
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 )) //para que dure un mes
                .signWith( getKey(), SignatureAlgorithm.HS256) //como se construye el metodo?
                .compact();
    }

    private Key getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); //esto es para que se pueda encriptar
    }


    public String getUsernameFromToken(String token) {

        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){

        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts //extraer las claims del token
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return getExperation(token).before(new Date());
    }

    private Date getExperation(String token) {

        return getClaim(token, Claims::getExpiration);

    }
}
