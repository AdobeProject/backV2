package com.example.demo.repository;

import com.example.demo.entity.SubCategory;
import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findAllBySubCategory(SubCategory subCategory);
	List<Course> findAllBySubCategory_Category(Category category);
	List<Course> findAllByNameContaining(String value);
	List<Course> findAllByCourseOwner(User owner);
}
