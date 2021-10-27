package com.example.demo.api.facade.user;


import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.user.UserDetailsResponseModel;
import com.example.demo.service.UserService.DefaultUserService;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserDetailsResponseModelBuilder implements UserDetailsResponseModelBuilder {

    private final DefaultUserService defaultUserService;
    private final UserMapper userMapper;

    public DefaultUserDetailsResponseModelBuilder(final DefaultUserService defaultUserService, UserMapper userMapper) {
        this.defaultUserService = defaultUserService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetailsResponseModel build(final Long userId) {
        final User user = defaultUserService.getById(userId);
        return userMapper.map(user);
    }
}
