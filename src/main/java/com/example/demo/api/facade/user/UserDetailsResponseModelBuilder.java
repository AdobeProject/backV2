package com.example.demo.api.facade.user;

import com.example.demo.model.user.UserDetailsResponseModel;

public interface UserDetailsResponseModelBuilder {

    UserDetailsResponseModel build(Long userId);
}
