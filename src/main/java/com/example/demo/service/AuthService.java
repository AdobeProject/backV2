package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRoleType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean isAuthorized(String token, String role) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        // TODO: 25.10.21 replace below mentioned deprecated parts
        JsonElement root = new JsonParser().parse(payload);
        String parsedRole = root.getAsJsonObject().getAsJsonObject().get("role").getAsString();
        String parsedUsername = root.getAsJsonObject().getAsJsonObject().get("username").getAsString();

        // TODO: 25.10.21 review
        User user = userService.getByEmail(parsedUsername);
        UserRoleType userRoleType = UserRoleType.getByName(parsedRole);

        if (parsedRole.equals(role)) {
            return true;
        }
        return false;
    }
}
