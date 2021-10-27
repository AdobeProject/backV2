package com.example.demo.api.facade.user;

import com.example.demo.api.facade.auth.AuthenticationApiFacade;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.user.*;
import com.example.demo.service.AuthService.AuthService;
import com.example.demo.service.UserService.DefaultUserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;

@Component
public class DefaultUserApiFacade implements UserApiFacade {

	private final DefaultUserService defaultUserService;
	private final AuthService authService;
	private final UserMapper userMapper;
	private final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder;
	private final AuthenticationApiFacade auth;

	public DefaultUserApiFacade(final DefaultUserService defaultUserService,
								AuthService authService, UserMapper userMapper, final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder, AuthenticationApiFacade auth) {
		this.defaultUserService = defaultUserService;
		this.authService = authService;
		this.userMapper = userMapper;
		this.userDetailsResponseModelBuilder = userDetailsResponseModelBuilder;
		this.auth = auth;
	}


	@Override
	public UserAuthenticationResponseModel create(final UserCreateRequestModel requestModel) {
		final Optional<User> u = defaultUserService.getByEmail(requestModel.getEmail());
		if (u.isPresent()) throw new RuntimeException("User already exists");
		final User user = defaultUserService.create(
				new UserCreateParams(
						requestModel.getEmail(),
						requestModel.getPassword(),
						requestModel.getFirstName(),
						requestModel.getSecondName(),
						requestModel.getRole()
				)
		);

		return auth.login(new UserAuthenticationRequestModel(requestModel.getEmail(),requestModel.getPassword()));

	}

	@Override
	public UserDetailsResponseModel update(String token) {

		Base64.Decoder decoder = Base64.getDecoder();
		String[] chunks = token.split("\\.");
		String payload = new String(decoder.decode(chunks[1]));

		// TODO: 25.10.21 replace below mentioned deprecated parts
		JsonElement root = new JsonParser().parse(payload);
		String parsedUsername = root.getAsJsonObject().getAsJsonObject().get("username").getAsString();
		Optional<User> userOptional = defaultUserService.getByEmail(parsedUsername);


		return userDetailsResponseModelBuilder.build(userOptional.get().getId());
	}


	public UserDetailsResponseModel refresh(String token) {
		Optional<User> user = authService.authenticate(token);
		if (user.isEmpty()) throw new NotFoundException("User doesnt exist");
		System.out.println(user.get().getFirstName());
		return userMapper.map(user.get());
	}
}
