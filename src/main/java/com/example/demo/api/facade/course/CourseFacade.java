package com.example.demo.api.facade.course;

import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;

import java.util.List;

public interface CourseFacade {

    CoursesDetailsResponse getAll();

    CourseDetailsResponse findById(Long id);

    CoursesDetailsResponse findByIds(List<Long> ids);

    CourseDetailsResponse create(CourseCreateRequestParams request);

    CourseDetailsResponse update(Long id, CourseCreateRequestParams request);

    List<CourseDetailsResponse> getAllBySubCategory(Long id);

    CoursesDetailsResponse getAllBySubCategories(List<Long> ids);

    List<CourseDetailsResponse> getAllByCategory(String name);

    // TODO: 25.10.21 need to clarify
    void delete(Long id);

    List<CourseDetailsResponse> search(String value);

    List<CourseDetailsResponse> getAllByOwner(String email);

    List<CourseDetailsResponse> getLast10();

    List<CourseDetailsResponse> getSuggestedCourses(Long id);

    CoursesDetailsResponse getAllUserEnrolledCourses(String token, Long courseId);
}
