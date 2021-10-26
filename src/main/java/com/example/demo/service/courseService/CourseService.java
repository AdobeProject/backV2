package com.example.demo.service.courseService;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.SubCategory;
import com.example.demo.entity.User;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.UserService.DefaultUserService;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.subCatecories.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final DefaultUserService defaultUserService;
	private final SubCategoryService subCategoryService;
	private final CategoryService categoryService;

	@Autowired
	public CourseService(CourseRepository courseRepository, DefaultUserService defaultUserService, SubCategoryService subCategoryService, CategoryService categoryService, CategoryService categoryService1) {
		this.courseRepository = courseRepository;
		this.defaultUserService = defaultUserService;
		this.subCategoryService = subCategoryService;
		this.categoryService = categoryService1;
	}

	public List<Course> getAll() {
		return courseRepository.findAll();
	}

	public Course findById(Long id) {
		return courseRepository.getById(id);
	}

	public Course create(CourseCreateRequestParams courseParams) {
		SubCategory subCategory = null;
		User user = null;
		if (courseParams.getSubCategory() != null) {
			final Optional<SubCategory> subCategoryOptional = subCategoryService.getById(courseParams.getSubCategory());
			if (subCategoryOptional.isEmpty()) throw new IllegalArgumentException("No such subcategory");
			else subCategory = subCategoryOptional.get();
		}
		System.out.println(courseParams.getOwner());

		Optional<User> userOptional = defaultUserService.getByEmail(courseParams.getOwner());
		if (userOptional.isEmpty()) throw new IllegalArgumentException("Unknown User");
		else user = userOptional.get();

		System.out.println(courseParams.getVideoURL());
		final Course course = new Course(
				courseParams.getName(),
				courseParams.getDescription(),
				courseParams.getImg(),
				courseParams.getVideoURL(),
				LocalDateTime.now(),
				user,
				subCategory
		);
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

    public List<Course> getAllBySubCategory(Long id) {
        Optional<SubCategory> subCategory = subCategoryService.getById(id);
        return courseRepository.findAllBySubCategory(subCategory.get());
    }

	public List<Course> getAllByCategory(String name) {
		Optional<Category> category = categoryService.getByName(name);
		return courseRepository.findAllBySubCategory_Category(category.get());
	}

    public List<Course> search(String value) {
		return courseRepository.findAllByNameContaining(value);
	}

}
