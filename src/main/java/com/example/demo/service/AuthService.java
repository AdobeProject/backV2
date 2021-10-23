package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRoleType;
import io.jsonwebtoken.JwtParser;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {

	private final UserService userService;

	public AuthService(UserService userService) {
		this.userService = userService;
	}

	public boolean isAuthorized(String token, String role) {
		Base64.Decoder decoder = Base64.getDecoder();
		String[] chunks = token.split("\\.");

		String email = new String(decoder.decode(chunks[0]));
		String parsedRole = new String(decoder.decode(chunks[1]));

		User user = userService.getByEmail(email);
		UserRoleType userRoleType = UserRoleType.getByName(role);
		return true;
	}
}
