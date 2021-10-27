package com.example.demo.service.history;

import com.example.demo.entity.User;

public interface HistoryService {
	void add(User user, Long course_id);
}
