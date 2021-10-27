package com.example.demo.service.user;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.exception.InvalidArgumentException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.user.ChangePasswordRequestModel;
import com.example.demo.model.user.UserCreateParams;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Regex;
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
        Regex.validEmail(createParams.getEmail());
        user.setEmail(createParams.getEmail());
        Regex.validFirstName(createParams.getFirstName());
        user.setFirstName(createParams.getFirstName());
        Regex.validSecondName(createParams.getSecondName());
        user.setSecondName(createParams.getSecondName());
        Regex.validPassword(createParams.getPassword());
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
        user.setFirstName(update.getFirstName());
        user.setSecondName(update.getSecondName());
        userRepository.save(user);
        return user;
    }

    public User changePassword(User user, ChangePasswordRequestModel passwords) {
        final boolean checkpw = BCrypt.checkpw(passwords.getOldPassword(), user.getPassword());
        if (!checkpw) {
            throw new InvalidArgumentException("Username or password does not exists");
        }
        final String salt = BCrypt.gensalt(10);
        final String hashed = BCrypt.hashpw(passwords.getNewPassword(), salt);
        user.setPassword(hashed);
        userRepository.save(user);
        return user;
    }


    @Override
    public void delete(String email) {
        Optional<User> user = getByEmail(email);
        if (user.isEmpty()) throw new NotFoundException("User not found with email: " + email + ".");
        userRepository.delete(user.get());
    }
}
