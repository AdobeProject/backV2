package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_generator")
    @SequenceGenerator(name = "categories_generator", sequenceName = "categories_id_seq", allocationSize = 1)
    private Long id;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "fk_history_user_id"))
    private User user;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "COURSE_ID", foreignKey = @ForeignKey(name = "fk_history_course_id"))
    private Course course;

    public History() {
    }

    public History(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
