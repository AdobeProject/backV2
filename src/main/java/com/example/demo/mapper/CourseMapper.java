package com.example.demo.mapper;

import com.example.demo.entity.Course;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {


	public CourseDetailsResponse map(Course course) {


		CourseDetailsResponse courseResponse = new CourseDetailsResponse(
				course.getId(),
				course.getName(),
				course.getDescription(),
				course.getImgId(),
				course.getVideoUrl(),
				course.getCourseOwner().getEmail(),
				course.getSubCategory() != null ? course.getSubCategory().getId() : null
		);

		return courseResponse;
	}

	public CoursesDetailsResponse map(List<Course> courses) {
		List<CourseDetailsResponse> list = new ArrayList<>(courses.size());

		for (Course course : courses)
			list.add(map(course));

		return new CoursesDetailsResponse(list);
	}
}
