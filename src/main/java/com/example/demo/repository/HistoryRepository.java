package com.example.demo.repository;

import com.example.demo.entity.History;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
	List<History> findAllByUser(User user);
}
