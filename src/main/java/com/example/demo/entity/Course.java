package com.example.demo.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COURSES")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_generator")
    @SequenceGenerator(name = "course_generator", sequenceName = "courses_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 2000)
    private String description;

    @Column(name = "IMG_ID", length = 16)
    private String imgId;

    @Column(name = "VIDEO_URL", nullable = false)
    private String videoUrl;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    //Like query


    @ManyToOne()
    @JoinColumn(name = "OWNER_USER_ID", foreignKey = @ForeignKey(name = "fk_course_owner_user_id"))
    private User courseOwner;

    @ManyToOne()
    @JoinColumn(name = "SUB_CATEGORY_ID", foreignKey = @ForeignKey(name = "fk_course_sub_category"))
    private SubCategory subCategory;

    public Course() {
    }

    public Course(String name,
				  String description,
				  String imgId,
				  String videoUrl,
				  LocalDateTime createdAt,
				  User courseOwner,
				  SubCategory subCategory) {
        this.name = name;
        this.description = description;
        this.imgId = imgId;
        this.videoUrl = videoUrl;
        this.createdAt = createdAt;
        this.courseOwner = courseOwner;
        this.subCategory = subCategory;
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

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCourseOwner() {
        return courseOwner;
    }

    public void setCourseOwner(User courseOwner) {
        this.courseOwner = courseOwner;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return new EqualsBuilder()
                .append(name, course.name)
                .append(description, course.description)
                .append(imgId, course.imgId)
                .append(videoUrl, course.videoUrl)
                .append(courseOwner, course.courseOwner)
                .append(subCategory, course.subCategory)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(description)
                .append(imgId)
                .append(videoUrl)
                .append(courseOwner)
                .append(subCategory)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("description", description)
                .append("imgId", imgId)
                .append("videoUrl", videoUrl)
                .append("createdAt", createdAt)
                .append("courseOwner", courseOwner)
                .append("subCategory", subCategory)
                .toString();
    }
}
