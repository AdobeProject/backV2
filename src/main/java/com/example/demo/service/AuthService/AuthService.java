package com.example.demo.service.AuthService;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRoleType;
import com.example.demo.service.UserService.DefaultUserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class AuthService implements AuthServiceInterFace {

    private final DefaultUserService defaultUserService;

    public AuthService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public boolean isAuthorized(String token, UserRoleType role) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        // TODO: 25.10.21 replace below mentioned deprecated parts
        JsonElement root = new JsonParser().parse(payload);
        String parsedRole = root.getAsJsonObject().getAsJsonObject().get("role").getAsString();

        if (UserRoleType.getByName(parsedRole) == role) return true;

        return UserRoleType.getByName(parsedRole) == UserRoleType.ADMIN;
    }

    public boolean isAuthenticated(String token){
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        // TODO: 25.10.21 replace below mentioned deprecated parts
        JsonElement root = new JsonParser().parse(payload);
        String parsedUsername = root.getAsJsonObject().getAsJsonObject().get("username").getAsString();
        Optional<User> userOptional = defaultUserService.getByEmail(parsedUsername);

        return userOptional.isEmpty();
    }

}
