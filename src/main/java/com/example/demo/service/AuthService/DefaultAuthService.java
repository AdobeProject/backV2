package com.example.demo.service.AuthService;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRoleType;
import com.example.demo.service.UserService.DefaultUserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Optional;


@Service
public class DefaultAuthService implements AuthService {


    private final Environment env;
    private final DefaultUserService defaultUserService;

    public DefaultAuthService(Environment env, DefaultUserService defaultUserService) {
        this.env = env;
        this.defaultUserService = defaultUserService;
    }

    public boolean isAuthorized(String token, UserRoleType role) {
        String parsedRole = parseJwt(token).getBody().get("role").toString();

        if (UserRoleType.getByName(parsedRole) == role) return true;

        return UserRoleType.getByName(parsedRole) == UserRoleType.ADMIN;
    }

    public boolean isAuthenticated(String token){
        return authenticate(token).isEmpty();
    }

    public Optional<User> authenticate(String token) {
        Jws<Claims> claimsJws = parseJwt(token);
        String parsedUsername = claimsJws.getBody().get("username").toString();
        System.out.println(parsedUsername);
        return defaultUserService.getByEmail(parsedUsername);
    }

    public void validate(String token) {

    }

    private Jws<Claims> parseJwt(String jwtString) {
        String secret = env.getProperty("secret");
        Key hmacKey = new SecretKeySpec(secret.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);

        return jwt;
    }
}
