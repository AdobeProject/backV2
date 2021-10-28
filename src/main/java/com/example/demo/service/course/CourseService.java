package com.example.demo.service.course;

import com.example.demo.entity.Course;
import com.example.demo.model.course.CourseCreateRequestParams;

import java.util.List;
import java.util.Optional;

public interface CourseService {
	List<Course> getAll();

	Optional<Course> findById(Long id);

	List<Course> findByIds(List<Long> ids);

	Course create(CourseCreateRequestParams courseParams);

	Course update(Long id, CourseCreateRequestParams update);

	void delete(Long id);

	List<Course> getAllBySubCategory(Long id);

	List<Course> getAllBySubCategories(List<Long> ids);

	List<Course> getAllByCategory(String name);

	List<Course> search(String value);

	List<Course> getAllByOwner(String email);

	List<Course> getLast10();

	List<Course> getSuggestedCourses(String name);
}
