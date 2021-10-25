package com.example.demo.api.facade.course;

import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;

public interface CourseFacade {

    CoursesDetailsResponse getAll();

    CourseDetailsResponse findById(Long id);

    CourseDetailsResponse create(CourseCreateRequestParams request);

    CourseDetailsResponse update(Long id, CourseCreateRequestParams request);

    // TODO: 25.10.21 need to clarify
    void delete(Long id);
}