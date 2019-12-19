package com.cognizant.studentrepositoryservice.dao;

import com.cognizant.studentrepositoryservice.model.Course;
import com.cognizant.studentrepositoryservice.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    Student student;
    Course course;

    @Before
    public void setUp() throws Exception {
        courseRepository.findAll().forEach(course -> courseRepository.deleteById(course.getName()));
        studentRepository.findAll().forEach(student -> studentRepository.deleteById(student.getStudentId()));

        student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student = studentRepository.save(student);

        course = new Course();
        course.setName("ENG1001");
        course.setScore(78);
        course.setStudent(student);
        courseRepository.save(course);
    }

    @Test
    public void findByStudentId() {
        List<Course> fromDao = courseRepository.findByStudent(student);

        assertEquals(1, fromDao.size());
    }

    @Test
    public void createGetGetAllDeleteCourses() {
        assertEquals(course, courseRepository.findById(course.getName()).orElse(null));
        assertEquals(1, courseRepository.findAll().size());

        courseRepository.deleteById(course.getName());
        assertEquals(0, courseRepository.findAll().size());
    }

    @Test
    public void updateCourse() {
        course.setScore(100);
        course = courseRepository.save(course);

        assertEquals(course, courseRepository.findById(course.getName()).orElse(null));
    }
}