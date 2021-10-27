package com.example.demo.service.history;

import com.example.demo.entity.Course;
import com.example.demo.entity.History;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.courseService.CourseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultHistoryService implements HistoryService{
	private final HistoryRepository historyRepository;
	private final CourseService courseService;

	public DefaultHistoryService(HistoryRepository historyRepository, CourseService courseService) {
		this.historyRepository = historyRepository;
		this.courseService = courseService;
	}

	@Override
	public Course add(User user, Long course_id) {
		Optional<Course> course = courseService.findById(course_id);
		if (course.isEmpty()) throw new NotFoundException("Course Not Found");
		History history = new History(user, course.get());
		historyRepository.save(history);
		return course.get();
	}
}
