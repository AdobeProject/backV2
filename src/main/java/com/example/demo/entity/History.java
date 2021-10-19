package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_generator")
    @SequenceGenerator(name = "categories_generator", sequenceName = "categories_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "fk_history_user_id"))
    private User user;

    @ManyToOne()
    @JoinColumn(name = "COURSE_ID", foreignKey = @ForeignKey(name = "fk_history_course_id"))
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
