package com.example.demo.service.auth;

import com.example.demo.entity.UserRoleType;

public interface AuthService {
    boolean isAuthorized(String token, UserRoleType role);
    boolean isAuthenticated(String token);
}
