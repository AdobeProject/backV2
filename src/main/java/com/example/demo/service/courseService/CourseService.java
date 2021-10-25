package com.example.demo.service.courseService;

import com.example.demo.entity.Course;
import com.example.demo.entity.SubCategory;
<<<<<<< Updated upstream:src/main/java/com/example/demo/service/CourseService.java
import com.example.demo.entity.User;
=======
>>>>>>> Stashed changes:src/main/java/com/example/demo/service/courseService/CourseService.java
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.subCatecories.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

<<<<<<< Updated upstream:src/main/java/com/example/demo/service/CourseService.java
	private final CourseRepository courseRepository;
	private final UserService userService;
	private final SubCategoryService subCategoryService;

	@Autowired
	public CourseService(CourseRepository courseRepository, UserService userService, SubCategoryService subCategoryService) {
		this.courseRepository = courseRepository;
		this.userService = userService;
		this.subCategoryService = subCategoryService;
	}
=======
    private final CourseRepository courseRepository;
    private final SubCategoryService subCategoryService;

    @Autowired
    public CourseService(CourseRepository courseRepository, SubCategoryService subCategoryService) {
        this.courseRepository = courseRepository;
        this.subCategoryService = subCategoryService;
    }
>>>>>>> Stashed changes:src/main/java/com/example/demo/service/courseService/CourseService.java

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
		Optional<User> userOptional = userService.getByEmail(courseParams.getOwner());
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

<<<<<<< Updated upstream:src/main/java/com/example/demo/service/CourseService.java
	public void delete(Long id) {
		Course courseOptional = courseRepository.getById(id);
		courseRepository.delete(courseOptional);
	}
=======
    public void delete(Long id) {
        Course courseOptional = courseRepository.getById(id);
        courseRepository.delete(courseOptional);
    }

    public List<Course> getAllBySubCategory(Long id) {
        SubCategory subCategory = subCategoryService.getById(id);
        return courseRepository.findAllBySubCategory(subCategory);
    }

>>>>>>> Stashed changes:src/main/java/com/example/demo/service/courseService/CourseService.java
}
