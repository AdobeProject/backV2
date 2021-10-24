package com.example.demo.model.user;

import java.util.List;

import com.example.demo.entity.UserRoleType;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDetailsResponseModel {

    private String email;

    private UserRoleType userRoleTypeList;

    public UserDetailsResponseModel() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public UserRoleType getUserRoleTypeList() {
        return userRoleTypeList;
    }

    public void setUserRole(UserRoleType userRoleTypeList) {
        this.userRoleTypeList = userRoleTypeList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("email", email)
            .append("userRoleTypeList", userRoleTypeList)
            .toString();
    }
}