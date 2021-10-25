package com.example.demo.repository;

<<<<<<< Updated upstream

=======
import com.example.demo.entity.SubCategory;
>>>>>>> Stashed changes
import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.SubCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
<<<<<<< Updated upstream
	List<Course> findAllBySubCategory(SubCategory subCategory);
	List<Course> findAllBySubCategory_Category(Category category);
=======
    List<Course> findAllBySubCategory(SubCategory subCategory);
    List<Course> findAllBySubCategory_Category(Category category);
>>>>>>> Stashed changes
}
