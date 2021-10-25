package com.example.demo.repository;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    Optional<SubCategory> findByName(String name);
    List<SubCategory> getAllByCategory(Category category);
    Optional<SubCategory> findById(Long id);
}
