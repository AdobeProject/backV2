package com.example.demo.api.facade.user;

import com.example.demo.model.user.UserCreateRequestModel;
import com.example.demo.model.user.UserDetailsResponseModel;

public interface UserApiFacade {

    UserDetailsResponseModel create(UserCreateRequestModel requestModel);
    UserDetailsResponseModel update(String token);

}
