package com.example.demo.api.facade.user;

import com.example.demo.entity.User;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.user.*;
import org.springframework.http.ResponseEntity;

public interface UserApiFacade {

    UserAuthenticationResponseModel create(UserCreateRequestModel requestModel);
    UserDetailsResponseModel update(String token, UpdateRequestModel newParams);
    UserDetailsResponseModel refresh(String token);
    CourseDetailsResponse add(String token, Long course_id);
    UserDetailsResponseModel changePassword(String token, ChangePasswordRequestModel passwords);

}
