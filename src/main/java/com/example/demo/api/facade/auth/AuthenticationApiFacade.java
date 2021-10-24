package com.example.demo.api.facade.auth;


import com.example.demo.model.user.UserAuthenticationRequestModel;
import com.example.demo.model.user.UserAuthenticationResponseModel;

public interface AuthenticationApiFacade {

    UserAuthenticationResponseModel login(UserAuthenticationRequestModel request);
}
