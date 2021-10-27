package com.example.demo.mapper;

import com.example.demo.entity.Course;
import com.example.demo.entity.History;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

	private final UserMapper userMapper;
	private final SubCategoryMapper subCategoryMapper;

	public CourseMapper(UserMapper userMapper, SubCategoryMapper subCategoryMapper) {
		this.userMapper = userMapper;
		this.subCategoryMapper = subCategoryMapper;
	}


	public CourseDetailsResponse map(Course course) {


		CourseDetailsResponse courseResponse = new CourseDetailsResponse(
				course.getId(),
				course.getName(),
				course.getDescription(),
				course.getImgId(),
				course.getVideoUrl(),
				userMapper.map(course.getCourseOwner()),
				subCategoryMapper.map(course.getSubCategory()),
				course.getCreatedAt()
		);

		return courseResponse;
	}

	public CoursesDetailsResponse map(List<Course> courses) {
		List<CourseDetailsResponse> list = new ArrayList<>(courses.size());

		for (Course course : courses)
			list.add(map(course));

		return new CoursesDetailsResponse(list);
	}

	public CoursesDetailsResponse mapHistories(List<History> histories) {
		List<CourseDetailsResponse> list = new ArrayList<>(histories.size());

		for (History history : histories)
			list.add(map(history.getCourse()));

		return new CoursesDetailsResponse(list);
	}
}
