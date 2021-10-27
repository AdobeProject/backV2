package com.example.demo.service.courseService;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.SubCategory;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.UserService.UserService;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.subCatecories.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCourseService implements CourseService {

	private final CourseRepository courseRepository;
	private final UserService userService;
	private final SubCategoryService subCategoryService;
	private final CategoryService categoryService;

	@Autowired
	public DefaultCourseService(CourseRepository courseRepository, UserService userService, SubCategoryService subCategoryService, CategoryService categoryService, CategoryService categoryService1) {
		this.courseRepository = courseRepository;
		this.userService = userService;
		this.subCategoryService = subCategoryService;
		this.categoryService = categoryService1;
	}

	public List<Course> getAll() {
		return courseRepository.findAll();
	}

	public Course findById(Long id) {
		return courseRepository.getById(id);
	}

	public List<Course> findByIds(List<Long> ids) {
		return courseRepository.findAllById(ids);
	}

	public Course create(CourseCreateRequestParams courseParams) {
		SubCategory subCategory = null;
		User user = null;
		if (courseParams.getSubCategory() != null) {
			final Optional<SubCategory> subCategoryOptional = subCategoryService.getById(courseParams.getSubCategory());
			if (subCategoryOptional.isEmpty()) throw new NotFoundException("No such subcategory");
			else subCategory = subCategoryOptional.get();
		}
		System.out.println(courseParams.getOwner());

		Optional<User> userOptional = userService.getByEmail(courseParams.getOwner());
		if (userOptional.isEmpty()) throw new NotFoundException("Unknown User");
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
		c.setName(update.getName());
		c.setDescription(update.getDescription());
		c.setImgId(update.getImg());
		c.setVideoUrl(update.getVideoURL());
		Optional<SubCategory> sub = subCategoryService.getById(update.getSubCategory());
		if (sub.isEmpty()) throw new NotFoundException("The subcategory with id: " + update.getSubCategory() + " doesn't exist.");
		c.setSubCategory(sub.get());
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

	public List<Course> getAllBySubCategories(List<Long> ids) {
		ArrayList<Course> courses = new ArrayList<>();

		List<SubCategory> subCategories = subCategoryService.getByIds(ids);
		for (SubCategory sub : subCategories) {
			for (Course course : courseRepository.findAllBySubCategory(sub)) {

				courses.add(course);
			}
		}
		return courses;
	}

	public List<Course> getAllByCategory(String name) {
		Optional<Category> category = categoryService.getByName(name);
		return courseRepository.findAllBySubCategory_Category(category.get());
	}

	public List<Course> search(String value) {
		return courseRepository.findAllByNameContaining(value);
	}

	@Override
	public List<Course> getAllByOwner(String email) {
		Optional<User> userOptional = userService.getByEmail(email);
		if (userOptional.isEmpty()) throw new NotFoundException("User not found with email:" + email);
		return courseRepository.findAllByCourseOwner(userOptional.get());
	}

	@Override
	public List<Course> getLast10() {
		List<Course> all = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		if (all.size() > 10)
			all = all.subList(0, 10);
		return all;
	}

}
