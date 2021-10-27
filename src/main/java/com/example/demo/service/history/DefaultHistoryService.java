package com.example.demo.service.history;

import com.example.demo.entity.Course;
import com.example.demo.entity.History;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.course.CourseService;
import com.example.demo.service.user.UserService;

import java.util.List;
import java.util.Optional;

public class DefaultHistoryService implements HistoryService{
	private final HistoryRepository historyRepository;
	private final UserService userService;
	private final CourseService courseService;

	public DefaultHistoryService(HistoryRepository historyRepository, UserService userService, CourseService courseService) {
		this.historyRepository = historyRepository;
		this.userService = userService;
		this.courseService = courseService;
	}

	@Override
	public void add(User user, Long course_id) {
		Optional<Course> course = courseService.findById(course_id);
		if (course.isEmpty()) throw new NotFoundException("Course Not Found");
		History history = new History(user, course.get());
		historyRepository.save(history);
	}

	public List<History> histories(User user) {
		return historyRepository.findAllByUser(user);
	}
}
