package com.example.demo.api.facade.course;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import com.example.demo.service.AuthService.AuthService;
import com.example.demo.service.UserService.UserService;
import com.example.demo.service.courseService.CourseService;
import com.example.demo.service.courseService.DefaultCourseService;
import com.example.demo.service.history.HistoryService;
import com.example.demo.service.subCatecories.SubCategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultCourseFacade implements CourseFacade {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final UserService userService;
    private final SubCategoryService subCategoryService;
    private final HistoryService historyService;
    private final AuthService authService;

    public DefaultCourseFacade(DefaultCourseService courseService, CourseMapper courseMapper, UserService userService, SubCategoryService subCategoryService, HistoryService historyService, AuthService authService) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.userService = userService;
        this.subCategoryService = subCategoryService;
        this.historyService = historyService;
        this.authService = authService;
    }

    @Override
    public CoursesDetailsResponse getAll() {
        return courseMapper.map(courseService.getAll());
    }

    @Override
    public CourseDetailsResponse findById(Long id) {
        Optional<Course> course = courseService.findById(id);
        if (course.isEmpty()) throw new NotFoundException("Course with Id dose not exist.");
        return courseMapper.map(course.get());
    }

    @Override
    public CoursesDetailsResponse findByIds(List<Long> ids) {
        List<Course> courses = courseService.findByIds(ids);
        System.out.println(courseMapper.map(courses).getCourses());
        return courseMapper.map(courses);
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
    public List<CourseDetailsResponse> getAllBySubCategory(Long id) {
        final List<Course> allBySubCategory = courseService.getAllBySubCategory(id);
        final List<CourseDetailsResponse> responseList = allBySubCategory.stream().map(course -> courseMapper.map(course)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public CoursesDetailsResponse getAllBySubCategories(List<Long> ids) {
        final List<Course> allBySubCategory = courseService.getAllBySubCategories(ids);
        final CoursesDetailsResponse responseList = courseMapper.map(allBySubCategory);
        return responseList;
    }

    @Override
    public List<CourseDetailsResponse> getAllByCategory(String name) {
        final List<Course> allByCategory = courseService.getAllByCategory(name);
        final List<CourseDetailsResponse> responseList = allByCategory.stream().map(course -> courseMapper.map(course)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public void delete(Long id) {
        courseService.delete(id);
    }

    @Override
    public List<CourseDetailsResponse> search(String value) {
        final List<Course> search = courseService.search(value);
        final List<CourseDetailsResponse> responseList = search.stream().map(course -> courseMapper.map(course)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public List<CourseDetailsResponse> getAllByOwner(String email) {
        final List<Course> courses = courseService.getAllByOwner(email);
        final List<CourseDetailsResponse> responseList = courses.stream().map(courseMapper::map).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public List<CourseDetailsResponse> getLast10() {
        final List<Course> courses = courseService.getLast10();
        final List<CourseDetailsResponse> responseList = courses.stream().map(courseMapper::map).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public List<CourseDetailsResponse> getSuggestedCourses(Long id) {
        final List<Course> courses = courseService.getSuggestedCourses(id);
        final List<CourseDetailsResponse> responseList = courses.stream().map(courseMapper::map).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public CoursesDetailsResponse getAllUserEnrolledCourses(String token, Long courseId) {
        Optional<User> user = authService.authenticate(token);
        if (user.isEmpty()) throw new NotFoundException("User Dose not Exist");
        return courseMapper.mapHistories(user.get().getEnrolledCourses());
    }
}
