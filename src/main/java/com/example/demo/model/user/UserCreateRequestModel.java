package com.example.demo.model.user;
import com.example.demo.entity.UserRoleType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateRequestModel {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("role")
    private UserRoleType role;

    public UserCreateRequestModel() {
    }

    public UserCreateRequestModel(final String email,
                                  final String password,
                                  final String firstName,
                                  final String secondName,
                                  final UserRoleType userRoleType) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = userRoleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    public UserRoleType getRole() {
        return role;
    }

    public void setRole(final UserRoleType role) {
        this.role = role;
    }
}
