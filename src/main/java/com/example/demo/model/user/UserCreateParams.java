package com.example.demo.model.user;

import com.example.demo.entity.UserRoleType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateParams {

    @JsonProperty("email")
    private final String email;

    @JsonProperty("password")
    private final String password;

    @JsonProperty("firstName")
    private final String firstName;

    @JsonProperty("secondName")
    private final String secondName;

    @JsonProperty("role")
    private final UserRoleType role;

    public UserCreateParams(final String email,
                            final String password,
                            final String firstName,
                            final String secondName, UserRoleType role) {
        if (email == null) {
            throw new IllegalArgumentException("The username should not be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("The password should not be null");
        }

        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role != null ? role : UserRoleType.STUDENT;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public UserRoleType getRole() {
        return role;
    }
}
