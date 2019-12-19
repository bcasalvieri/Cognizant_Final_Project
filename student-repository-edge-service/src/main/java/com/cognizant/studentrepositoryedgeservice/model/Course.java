package com.cognizant.studentrepositoryedgeservice.model;

import java.util.Objects;

public class Course {

    private Student student;
    private String name;
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
