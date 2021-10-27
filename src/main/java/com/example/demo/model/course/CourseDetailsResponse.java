package com.example.demo.model.course;

import com.example.demo.model.user.UserDetailsResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public class CourseDetailsResponse {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("img")
	private String img;

	@JsonProperty("video")
	private String videoURL;

	@JsonProperty("owner")
	private UserDetailsResponseModel owner;

	@JsonProperty("sub_category_id")
	private Long subCategory;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	public CourseDetailsResponse() {
	}

	public CourseDetailsResponse(Long id, String name, String description, String img, String videoURL, UserDetailsResponseModel owner, Long subCategory, LocalDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.img = img;
		this.videoURL = videoURL;
		this.owner = owner;
		this.subCategory = subCategory;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public UserDetailsResponseModel getOwner() {
		return owner;
	}

	public void setOwner(UserDetailsResponseModel owner) {
		this.owner = owner;
	}

	public Long getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Long subCategory) {
		this.subCategory = subCategory;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
				.toString();
	}
}
