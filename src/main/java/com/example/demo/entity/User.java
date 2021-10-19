package com.example.demo.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @SequenceGenerator(name = "users_generator", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "IMG_ID", length = 16)
    private String imgId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE", nullable = false, length = 50)
    private UserRoleType role;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<History> enrolledCourses;

    public User() {

    }

    public User(String name,
                String username,
                String email,
                String password,
                String imgId,
                UserRoleType role,
                LocalDateTime createdAt) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imgId = imgId;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public UserRoleType getRole() {
        return role;
    }

    public void setRole(UserRoleType role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<History> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<History> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(name, user.name)
                .append(username, user.username)
                .append(email, user.email)
                .append(password, user.password)
                .append(imgId, user.imgId)
                .append(role, user.role)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(username)
                .append(email)
                .append(password)
                .append(imgId)
                .append(role)
                .toHashCode();
    }
}
