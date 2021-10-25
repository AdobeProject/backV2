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
public class AuthService {

    private final DefaultUserService defaultUserService;

    public AuthService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public boolean isAuthorized(String token, String role) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        // TODO: 25.10.21 replace below mentioned deprecated parts
        JsonElement root = new JsonParser().parse(payload);
        String parsedRole = root.getAsJsonObject().getAsJsonObject().get("role").getAsString();
        String parsedUsername = root.getAsJsonObject().getAsJsonObject().get("email").getAsString();

        // TODO: 25.10.21 review
        Optional<User> userOptional = defaultUserService.getByEmail(parsedUsername);
        if (userOptional.isEmpty()) return false;

        User user = userOptional.get();
        UserRoleType userRoleType = UserRoleType.getByName(parsedRole);

        if (userRoleType != user.getRole()) return false;
        if (user.getRole() == UserRoleType.ADMIN) return true;

        return parsedRole.equals(role);

    }
}
