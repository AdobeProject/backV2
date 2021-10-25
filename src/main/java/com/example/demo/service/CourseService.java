package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getAll() {
		return courseRepository.findAll();
	}

	public Course findById(Long id) {
		return courseRepository.getById(id);
	}

	public Course create(CourseCreateRequestParams courseParams) {
		final Course course = new Course();
		course.setCreatedAt(LocalDateTime.now());
		course.setDescription(courseParams.getDescription());
		course.setName(courseParams.getName());
		course.setVideoUrl(courseParams.getVideoURL());
		System.out.println(courseParams.getVideoURL());

		return courseRepository.save(course);
	}

	public Course update(Long id, CourseCreateRequestParams update) {
		Course c = courseRepository.getById(id);
		courseRepository.save(c);
		return c;
	}

	public void delete(Long id) {
		Course courseOptional = courseRepository.getById(id);
		courseRepository.delete(courseOptional);
	}
}
