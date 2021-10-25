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


		CourseDetailsResponse courseResponse = new CourseDetailsResponse();

		courseResponse.setId(course.getId());
		courseResponse.setName(course.getName());
		courseResponse.setDescription(course.getDescription());
		courseResponse.setImg(course.getImgId());
		if (course.getCourseOwner() != null)
			courseResponse.setOwner(course.getCourseOwner().getId());
		if (course.getSubCategory() != null)
			courseResponse.setSubCategory(course.getSubCategory().getId());
		return courseResponse;
	}

	public CoursesDetailsResponse map(List<Course> courses) {
		List<CourseDetailsResponse> list = new ArrayList<>(courses.size());

		for (Course course : courses)
			list.add(map(course));

		return new CoursesDetailsResponse(list);
	}
}
