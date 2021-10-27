package com.example.demo.controllers;

import com.example.demo.api.facade.auth.DefaultAuthenticationApiFacade;
import com.example.demo.api.facade.user.UserApiFacade;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.user.*;
import com.example.demo.service.AuthService.DefaultAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserApiFacade userApiFacade;
	private final DefaultAuthenticationApiFacade defaultAuthenticationApiFacade;
	private final DefaultAuthService authService;

	public UserController(UserApiFacade userApiFacade, DefaultAuthenticationApiFacade defaultAuthenticationApiFacade, DefaultAuthService authService) {
		this.userApiFacade = userApiFacade;
		this.defaultAuthenticationApiFacade = defaultAuthenticationApiFacade;
		this.authService = authService;
	}

	@PostMapping("/signup")
	public UserAuthenticationResponseModel signUp(@RequestBody UserCreateRequestModel userCreateParams) {
		return userApiFacade.create(userCreateParams);
	}

	@PostMapping("/login")
	public UserAuthenticationResponseModel login(@RequestBody UserAuthenticationRequestModel requestModel) {
		return defaultAuthenticationApiFacade.login(requestModel);
	}

	@GetMapping("/")
	public ResponseEntity<?> refresh(@RequestHeader("Authorization") String token) {
		UserDetailsResponseModel user = userApiFacade.refresh(token);
		if (authService.isAuthenticated(token)) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not exist");
	}

	@PostMapping("/add/{id}")
	public CourseDetailsResponse add(@RequestHeader("Authorization") String token, @PathVariable("id") Long courseId) {
		return userApiFacade.add(token, courseId);
	}
}
