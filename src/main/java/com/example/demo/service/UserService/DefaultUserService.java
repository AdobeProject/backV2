package com.example.demo.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.user.UserCreateParams;
import com.example.demo.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{

    private final UserRepository userRepository;

    public DefaultUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(final UserCreateParams createParams) {
        final String salt = BCrypt.gensalt(10);

        final User user = new User();
        user.setEmail(createParams.getEmail());
        user.setFirstName(createParams.getFirstName());
        user.setSecondName(createParams.getSecondName());
        user.setPassword(
            BCrypt.hashpw(createParams.getPassword(), salt)
        );
        user.setRole(createParams.getRole());
        user.setCreatedAt(LocalDateTime.now());

        final User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Optional<User> getByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public User getById(final Long id) {
        final Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(
                String.format("The user not found for the id %s", id)
            );
        }
        return userOptional.get();
    }

    @Override
    public User update(String email, UserCreateParams update) {
        Optional<User> userOptional = getByEmail(email);
        if (userOptional.isEmpty()) throw new NotFoundException("User not found with email: " + email + ".");
        User user = userOptional.get();

        return null;
    }

    @Override
    public void delete(String email) {
        Optional<User> user = getByEmail(email);
        if (user.isEmpty()) throw new NotFoundException("User not found with email: " + email + ".");
        userRepository.delete(user.get());
    }
}
