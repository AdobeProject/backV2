package com.example.demo.service.UserService;

import com.example.demo.entity.User;
import com.example.demo.model.user.UserCreateParams;

import java.util.Optional;

public interface UserService {
	User create(final UserCreateParams createParams);
	Optional<User> getByEmail(final String email);
	User getById(final Long id);
}