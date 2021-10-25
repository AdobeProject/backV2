package com.example.demo.api.facade.course;

import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Component;

@Component
public class DefaultCourseFacade implements CourseFacade {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public DefaultCourseFacade(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @Override
    public CoursesDetailsResponse getAll() {
        return courseMapper.map(courseService.getAll());
    }

    @Override
    public CourseDetailsResponse findById(Long id) {
        Course course = courseService.findById(id);
        return courseMapper.map(course);
    }

    @Override
    public CourseDetailsResponse create(CourseCreateRequestParams request) {
        Course savedCourse = courseService.create(request);
        return courseMapper.map(savedCourse);
    }

    @Override
    public CourseDetailsResponse update(Long id, CourseCreateRequestParams request) {
        Course updatedCourse = courseService.update(id, request);
        return courseMapper.map(updatedCourse);
    }

    @Override
    public void delete(Long id) {
        courseService.delete(id);
    }


}
