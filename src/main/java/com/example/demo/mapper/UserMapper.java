package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.user.UserDetailsResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDetailsResponseModel map(User user) {
        UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();

        userDetailsResponseModel.setUserRole(user.getRole());
        userDetailsResponseModel.setEmail(user.getEmail());
        return userDetailsResponseModel;
    }
}
