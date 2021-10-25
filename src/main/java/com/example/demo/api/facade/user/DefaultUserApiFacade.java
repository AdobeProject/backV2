package com.example.demo.api.facade.user;

import com.example.demo.entity.User;
import com.example.demo.model.user.UserCreateParams;
import com.example.demo.model.user.UserCreateRequestModel;
import com.example.demo.model.user.UserDetailsResponseModel;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserApiFacade implements UserApiFacade {

	private final UserService userService;
	private final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder;

	public DefaultUserApiFacade(final UserService userService,
								final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder) {
		this.userService = userService;
		this.userDetailsResponseModelBuilder = userDetailsResponseModelBuilder;
	}


	@Override
	public UserDetailsResponseModel create(final UserCreateRequestModel requestModel) {
		final User user = userService.create(
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
}
