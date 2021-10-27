package com.example.demo.service.auth;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRoleType;

import java.util.Optional;

public interface AuthService {
    boolean isAuthorized(String token, UserRoleType role);
    boolean isAuthenticated(String token);
    Optional<User> authenticate(String token);
}
