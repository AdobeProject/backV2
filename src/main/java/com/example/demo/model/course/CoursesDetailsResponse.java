package com.example.demo.model.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CoursesDetailsResponse {
	@JsonProperty("courses")
	private List<CourseDetailsResponse> courses;

	@JsonProperty("length")
	private Integer length;

	public CoursesDetailsResponse(List<CourseDetailsResponse> courses) {
		this.courses = courses;
		this.length = courses.size();
	}

	public List<CourseDetailsResponse> getCourses() {
		return courses;
	}

	public Integer getLength() {
		return length;
	}

	public void setUsers(List<CourseDetailsResponse> courses) {
		this.courses = courses;
		this.length = courses.size();
	}
}
