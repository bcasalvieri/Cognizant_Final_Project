package com.cognizant.studentrepositoryedgeservice.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class StudentViewModel {

    private Integer studentId;
    @NotEmpty(message = "Please enter a first name.")
    private String firstName;
    @NotEmpty(message = "Please enter a last name.")
    private String lastName;
    private List<Course> courses;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentViewModel that = (StudentViewModel) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, courses);
    }

    @Override
    public String toString() {
        return "StudentViewModel{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courses=" + courses +
                '}';
    }
}
