package com.example.demo.controllers;

import com.example.demo.api.facade.auth.DefaultAuthenticationApiFacade;
import com.example.demo.api.facade.user.DefaultUserApiFacade;
import com.example.demo.api.facade.user.UserApiFacade;
import com.example.demo.model.user.*;
import com.example.demo.service.AuthService.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private final UserApiFacade userApiFacade;
	private final DefaultAuthenticationApiFacade defaultAuthenticationApiFacade;
	private final AuthService authService;
	private final DefaultUserApiFacade defaultUserApiFacade;

	public UserController(UserApiFacade userApiFacade, DefaultAuthenticationApiFacade defaultAuthenticationApiFacade, AuthService authService, DefaultUserApiFacade defaultUserApiFacade) {
		this.userApiFacade = userApiFacade;
		this.defaultAuthenticationApiFacade = defaultAuthenticationApiFacade;
		this.authService = authService;
		this.defaultUserApiFacade = defaultUserApiFacade;
	}

	@PostMapping("/signup")
	public UserDetailsResponseModel signUp(@RequestBody UserCreateRequestModel userCreateParams) {
		return userApiFacade.create(userCreateParams);
	}

	@PostMapping("/login")
	public UserAuthenticationResponseModel login(@RequestBody UserAuthenticationRequestModel requestModel) {
		return defaultAuthenticationApiFacade.login(requestModel);
	}

	@GetMapping("/")
	public ResponseEntity<?> refresh(@RequestHeader("Authorization") String token) {
		if (authService.isAuthenticated(token)) {
			return ResponseEntity.ok(defaultUserApiFacade.update(token));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not exist");
	}
}
