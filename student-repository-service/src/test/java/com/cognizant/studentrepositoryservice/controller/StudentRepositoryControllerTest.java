package com.cognizant.studentrepositoryservice.controller;

import com.cognizant.studentrepositoryservice.dao.CourseRepository;
import com.cognizant.studentrepositoryservice.dao.StudentRepository;
import com.cognizant.studentrepositoryservice.model.Course;
import com.cognizant.studentrepositoryservice.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentRepositoryController.class)
public class StudentRepositoryControllerTest {
    
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    CourseRepository courseRepository;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    
    Student inputStudent;
    Student outputStudent;
    Course course;
    List<Course> courses;

    @Before
    public void setUp() throws Exception {
        inputStudent = new Student();
        inputStudent.setFirstName("John");
        inputStudent.setLastName("Doe");

        outputStudent = new Student();
        outputStudent.setStudentId(1);
        outputStudent.setFirstName("John");
        outputStudent.setLastName("Doe");
        
        course = new Course();
        course.setName("ENG1001");
        course.setScore(78);
        course.setStudent(outputStudent);

        courses = new ArrayList<>(Collections.singleton(course));

        setUpMocks();
    }

    @Test
    public void createStudent() throws Exception {
        String inputJson = mapper.writeValueAsString(inputStudent);
        String outputJson = mapper.writeValueAsString(outputStudent);

        mvc.perform(post("/students")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getStudent() throws Exception {
        String outputJson = mapper.writeValueAsString(outputStudent);

        mvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getStudentThrowException() throws Exception {
        mvc.perform(get("/students/2"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createCourse() throws Exception {
        String inputJson = mapper.writeValueAsString(course);
        String outputJson = mapper.writeValueAsString(course);

        mvc.perform(post("/courses")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getCoursesByStudentId() throws Exception {
        String outputJson = mapper.writeValueAsString(courses);

        mvc.perform(get("/courses/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    private void setUpMocks() {
        when(studentRepository.save(inputStudent)).thenReturn(outputStudent);
        when(studentRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(outputStudent));
        when(studentRepository.findById(2)).thenThrow(new IllegalArgumentException("No student found with id 2"));

        courses = new ArrayList<>(Collections.singleton(course));
        when(courseRepository.save(course)).thenReturn(course);
        when(courseRepository.findByStudent(outputStudent)).thenReturn(courses);
    }
}