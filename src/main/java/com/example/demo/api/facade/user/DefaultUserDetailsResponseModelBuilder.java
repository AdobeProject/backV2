package com.example.demo.api.facade.user;


import com.example.demo.entity.User;
import com.example.demo.model.user.UserDetailsResponseModel;
import com.example.demo.service.UserService.DefaultUserService;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserDetailsResponseModelBuilder implements UserDetailsResponseModelBuilder {

    private final DefaultUserService defaultUserService;

    public DefaultUserDetailsResponseModelBuilder(final DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @Override
    public UserDetailsResponseModel build(final Long userId) {
        final User user = defaultUserService.getById(userId);



        final UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
        userDetailsResponseModel.setEmail(user.getEmail());
        userDetailsResponseModel.setUserRole(user.getRole());
        userDetailsResponseModel.setFirstName(user.getFirstName());
        userDetailsResponseModel.setLastName(user.getSecondName());

        return userDetailsResponseModel;
    }
}
