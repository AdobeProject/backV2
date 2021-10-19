package com.example.demo.model.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CourseDetailsResponse {

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("img")
	private String img;

	@JsonProperty("video")
	private String videoURL;

	@JsonProperty("owner_id")
	private Long owner;

	@JsonProperty("sub_category_id")
	private Long subCategory;

	@JsonProperty("quiz")
	private Long quiz;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public Long getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Long subCategory) {
		this.subCategory = subCategory;
	}

	public Long getQuiz() {
		return quiz;
	}

	public void setQuiz(Long quiz) {
		this.quiz = quiz;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("name", name)
				.append("description", description)
				.append("img", img)
				.append("videoURL", videoURL)
				.append("owner", owner)
				.append("subCategory", subCategory)
				.append("quiz", quiz)
				.toString();
	}
}
