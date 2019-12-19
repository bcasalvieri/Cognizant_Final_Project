package com.cognizant.studentrepositoryservice.controller;

import com.cognizant.studentrepositoryservice.dao.CourseRepository;
import com.cognizant.studentrepositoryservice.dao.StudentRepository;
import com.cognizant.studentrepositoryservice.model.Course;
import com.cognizant.studentrepositoryservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRepositoryController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new IllegalArgumentException("No student found with id " + id);
        return student;
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/courses/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getCoursesByStudentId(@PathVariable Integer studentId) {
        return courseRepository.findByStudent(studentRepository.findById(studentId).orElse(null));
    }
}
