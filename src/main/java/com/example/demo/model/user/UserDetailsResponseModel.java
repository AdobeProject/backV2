package com.example.demo.model.user;

import java.util.List;

import com.example.demo.entity.UserRoleType;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDetailsResponseModel {

    private String firstName;

    private String lastName;

    private String email;

    private UserRoleType userRole;

    public UserDetailsResponseModel() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public UserRoleType getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleType userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("userRole", userRole)
                .toString();
    }
}