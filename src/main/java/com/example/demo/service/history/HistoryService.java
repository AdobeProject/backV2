package com.example.demo.service.history;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;

public interface HistoryService {
	Course add(User user, Long course_id);
}
