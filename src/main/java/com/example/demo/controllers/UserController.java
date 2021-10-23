package com.example.demo.controllers;

import com.example.demo.api.facade.auth.DefaultAuthenticationApiFacade;
import com.example.demo.api.facade.user.UserApiFacade;
import com.example.demo.model.user.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserApiFacade userApiFacade;
	private final DefaultAuthenticationApiFacade defaultAuthenticationApiFacade;

	public UserController(UserApiFacade userApiFacade, DefaultAuthenticationApiFacade defaultAuthenticationApiFacade) {
		this.userApiFacade = userApiFacade;
		this.defaultAuthenticationApiFacade = defaultAuthenticationApiFacade;
	}

	@PostMapping("/signup")
	public UserDetailsResponseModel signUp(@RequestBody UserCreateRequestModel userCreateParams) {
		return userApiFacade.create(userCreateParams);
	}

	@PostMapping("/login")
	public UserAuthenticationResponseModel login(@RequestBody UserAuthenticationRequestModel requestModel) {
		return defaultAuthenticationApiFacade.login(requestModel);
	}
}
