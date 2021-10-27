package com.example.demo.api.facade.user;

import com.example.demo.model.user.UserAuthenticationResponseModel;
import com.example.demo.model.user.UserCreateRequestModel;
import com.example.demo.model.user.UserDetailsResponseModel;

public interface UserApiFacade {

    UserAuthenticationResponseModel create(UserCreateRequestModel requestModel);
    UserDetailsResponseModel update(String token);
    UserDetailsResponseModel refresh(String token);

}
