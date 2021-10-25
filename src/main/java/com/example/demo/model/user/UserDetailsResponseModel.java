package com.example.demo.model.user;

import java.util.List;

import com.example.demo.entity.UserRoleType;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDetailsResponseModel {

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("email", email)
            .append("userRoleTypeList", userRole)
            .toString();
    }
}