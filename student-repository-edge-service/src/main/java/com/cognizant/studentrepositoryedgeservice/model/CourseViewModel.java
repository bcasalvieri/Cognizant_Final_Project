package com.cognizant.studentrepositoryedgeservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CourseViewModel {

    @NotEmpty(message = "Please enter a name.")
    private String name;
    @NotNull(message = "Please enter a score.")
    private Integer score;

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
        CourseViewModel that = (CourseViewModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    @Override
    public String toString() {
        return "CourseViewModel{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
