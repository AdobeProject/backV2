package com.example.demo.api.facade.auth;

import com.example.demo.api.facade.user.UserDetailsResponseModelBuilder;
import com.example.demo.entity.User;
import com.example.demo.model.user.UserAuthenticationRequestModel;
import com.example.demo.model.user.UserAuthenticationResponseModel;
import com.example.demo.service.UserService.DefaultUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.security.Key;
import java.util.Optional;

@Component
public class DefaultAuthenticationApiFacade implements AuthenticationApiFacade {
	private final DefaultUserService defaultUserService;
	private final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder;

	public DefaultAuthenticationApiFacade(final DefaultUserService defaultUserService,
										  final UserDetailsResponseModelBuilder userDetailsResponseModelBuilder) {
		this.defaultUserService = defaultUserService;
		this.userDetailsResponseModelBuilder = userDetailsResponseModelBuilder;
	}

	@Override
	public UserAuthenticationResponseModel login(final UserAuthenticationRequestModel request) {
		final Optional<User> userOptional = defaultUserService.getByEmail(request.getEmail());
		if (userOptional.isEmpty()) throw new EntityNotFoundException();
		final User user = userOptional.get();
		final boolean checkpw = BCrypt.checkpw(request.getPassword(), user.getPassword());
		if (!checkpw) {
			throw new IllegalArgumentException("Username or password does not exists");
		}

		Key key = Keys.hmacShaKeyFor("'7V:lT@4sfsdterU6b~O(_nt5W0lJl@`wE".getBytes());
		String token = Jwts.builder()
				.claim("email", user.getEmail())
				.claim("role", user.getRole())
				.signWith(key).compact();

		final UserAuthenticationResponseModel responseModel = new UserAuthenticationResponseModel();
		responseModel.setToken(token);
		responseModel.setUserDetails(userDetailsResponseModelBuilder.build(user.getId()));
		return responseModel;
	}

}
