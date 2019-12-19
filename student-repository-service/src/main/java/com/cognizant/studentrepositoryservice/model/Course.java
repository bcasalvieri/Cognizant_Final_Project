package com.cognizant.studentrepositoryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "courses")
public class Course {

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @Id
    private String name;
    @Column(nullable = false)
    private Integer score;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(student, course.student) &&
                Objects.equals(name, course.name) &&
                Objects.equals(score, course.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, name, score);
    }

    @Override
    public String toString() {
        return "Course{" +
                "student=" + student +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
