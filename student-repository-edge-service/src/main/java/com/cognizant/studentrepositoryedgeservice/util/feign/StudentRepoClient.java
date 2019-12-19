package com.cognizant.studentrepositoryedgeservice.util.feign;

import com.cognizant.studentrepositoryedgeservice.model.Course;
import com.cognizant.studentrepositoryedgeservice.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "student-repository-service")
public interface StudentRepoClient {

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student);

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Integer id);

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course);

    @GetMapping("/courses/{studentId}")
    public List<Course> getCoursesByStudentId(@PathVariable Integer studentId);
}
