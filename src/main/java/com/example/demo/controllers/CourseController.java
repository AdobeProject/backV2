package com.example.demo.controllers;

import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.model.course.CourseCreateParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import com.example.demo.service.AuthService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
	private final CourseService courseService;
	private final CourseMapper courseMapper;
	private final AuthService authService;

	public CourseController(CourseService courseService, CourseMapper courseMapper, AuthService authService) {
		this.courseService = courseService;
		this.courseMapper = courseMapper;
		this.authService = authService;
	}

	//~CGLIB
	//JDK proxy
	@GetMapping("/")
	public CoursesDetailsResponse getAll() {
		return courseMapper.map(courseService.getAll());
	}

	@GetMapping("/{id}")
	public CourseDetailsResponse findById(@PathVariable("id") Long id) {
		Course course = courseService.findById(id);
		CourseDetailsResponse response = courseMapper.map(course);
		return response;
	}

	@PostMapping("/")
	public void create(@RequestBody CourseCreateParams course, @RequestHeader("Authorization") String token, HttpServletResponse response) {
		if (authService.isAuthorized(token, "admin"))
			courseService.create(course);
		else
			response.setStatus(403);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable("id") Long id, @RequestBody CourseCreateParams update, @RequestHeader("Authorization") String token, HttpServletResponse response) {
		if (authService.isAuthorized(token, "admin"))
			courseService.update(id, update);
		else
			response.setStatus(403);
	}

	@DeleteMapping("/")
	public void delete(@RequestParam Long id, @RequestHeader("Authorization") String token, HttpServletResponse response) {
		if (authService.isAuthorized(token, "admin"))
			courseService.delete(id);
		else
			response.setStatus(403);
	}
}
