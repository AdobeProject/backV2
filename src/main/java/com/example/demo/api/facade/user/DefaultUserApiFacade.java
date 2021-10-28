package com.example.demo.api.facade.user;

import com.example.demo.api.facade.auth.AuthenticationApiFacade;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.user.*;
import com.example.demo.service.auth.AuthService;
import com.example.demo.service.history.HistoryService;
import com.example.demo.service.user.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultUserApiFacade implements UserApiFacade {

	private final UserService defaultUserService;
	private final AuthService authService;
	private final UserMapper userMapper;
	private final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder;
	private final AuthenticationApiFacade auth;
	private final HistoryService historyService;
	private final CourseMapper courseMapper;

	public DefaultUserApiFacade(final UserService defaultUserService,
								AuthService authService, UserMapper userMapper, final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder, AuthenticationApiFacade auth, HistoryService historyService, CourseMapper courseMapper) {
		this.defaultUserService = defaultUserService;
		this.authService = authService;
		this.userMapper = userMapper;
		this.userDetailsResponseModelBuilder = userDetailsResponseModelBuilder;
		this.auth = auth;
		this.historyService = historyService;
		this.courseMapper = courseMapper;
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
	public UserDetailsResponseModel update(String token, UserCreateParams newParams) {
		Optional<User> user = authService.authenticate(token);
		if (user.isEmpty()) throw new NotFoundException("User doesnt exist");
		User updated = defaultUserService.update(user.get(), newParams);
		return userMapper.map(updated);
	}


	public UserDetailsResponseModel refresh(String token) {
		Optional<User> user = authService.authenticate(token);
		if (user.isEmpty()) throw new NotFoundException("User doesnt exist");
		System.out.println(user.get().getFirstName());
		return userMapper.map(user.get());
	}

	@Override
	public CourseDetailsResponse add(String token, Long course_id) {
		Optional<User> optionalUser = authService.authenticate(token);
		if (optionalUser.isEmpty()) throw new NotFoundException("User Not Found");
		return courseMapper.map(historyService.add(optionalUser.get(), course_id));
	}

	@Override
	public UserDetailsResponseModel changePassword(String token, ChangePasswordRequestModel passwords) {
		Optional<User> optionalUser = authService.authenticate(token);
		if (optionalUser.isEmpty()) throw new NotFoundException("User Not Found");
		User res = defaultUserService.changePassword(optionalUser.get(), passwords);
		return userMapper.map(res);
	}


}
