package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.user.UserDetailsResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDetailsResponseModel map(User user) {
        final UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
        userDetailsResponseModel.setEmail(user.getEmail());
        userDetailsResponseModel.setUserRole(user.getRole());
        userDetailsResponseModel.setFirstName(user.getFirstName());
        userDetailsResponseModel.setLastName(user.getSecondName());
        return userDetailsResponseModel;
    }
}
