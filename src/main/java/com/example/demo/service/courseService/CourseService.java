package com.example.demo.service.courseService;

import com.example.demo.entity.Course;
import com.example.demo.model.course.CourseCreateRequestParams;

import java.util.List;

public interface CourseService {
	List<Course> getAll();
	Course findById(Long id);
	List<Course> findByIds(List<Long> ids);
	Course create(CourseCreateRequestParams courseParams);
	Course update(Long id, CourseCreateRequestParams update);
	void delete(Long id);
	List<Course> getAllBySubCategory(Long id);
	List<Course> getAllBySubCategories(List<Long> ids);
	List<Course> getAllByCategory(String name);
	List<Course> search(String value);
	List<Course> getAllByOwner(String email);
}
