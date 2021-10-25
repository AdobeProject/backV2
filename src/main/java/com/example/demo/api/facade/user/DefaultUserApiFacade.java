package com.example.demo.api.facade.user;

import com.example.demo.entity.User;
import com.example.demo.model.user.UserCreateParams;
import com.example.demo.model.user.UserCreateRequestModel;
import com.example.demo.model.user.UserDetailsResponseModel;
import com.example.demo.service.UserService.DefaultUserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;

@Component
public class DefaultUserApiFacade implements UserApiFacade {

	private final DefaultUserService defaultUserService;
	private final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder;

	public DefaultUserApiFacade(final DefaultUserService defaultUserService,
								final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder) {
		this.defaultUserService = defaultUserService;
		this.userDetailsResponseModelBuilder = userDetailsResponseModelBuilder;
	}


	@Override
	public UserDetailsResponseModel create(final UserCreateRequestModel requestModel) {
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
		final UserDetailsResponseModel build = userDetailsResponseModelBuilder.build(user.getId());
		return build;
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
}
