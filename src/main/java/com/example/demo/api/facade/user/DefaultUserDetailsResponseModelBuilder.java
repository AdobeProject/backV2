package com.example.demo.api.facade.user;


import com.example.demo.entity.User;
import com.example.demo.model.user.UserDetailsResponseModel;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserDetailsResponseModelBuilder implements UserDetailsResponseModelBuilder {

    private final UserService userService;

    public DefaultUserDetailsResponseModelBuilder(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetailsResponseModel build(final Long userId) {
        final User user = userService.getById(userId);

        final UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
        userDetailsResponseModel.setEmail(user.getEmail());
        userDetailsResponseModel.setUserRole(user.getRole());

        return userDetailsResponseModel;
    }
}
