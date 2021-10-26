package com.example.demo.service.AuthService;

import com.example.demo.entity.UserRoleType;

public interface AuthServiceInterFace {
    public boolean isAuthorized(String token, UserRoleType role);
    public boolean isAuthenticated(String token);
}
