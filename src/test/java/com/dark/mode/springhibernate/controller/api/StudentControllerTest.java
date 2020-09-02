package com.dark.mode.springhibernate.controller.api;

import com.dark.mode.springhibernate.controller.api.v1.StudentController;
import com.dark.mode.springhibernate.dto.StudentDTO;
import com.dark.mode.springhibernate.model.Student;
import com.dark.mode.springhibernate.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private final Map<String, List<Student>> stringListMap = new HashMap<>();
    private final List<Student> studentList = new ArrayList<>();
    @MockBean
    private StudentService studentService;
    @MockBean
    private StudentDTO studentDTO;
    @Autowired
    private MockMvc mockMvc;

    private static Object answer(InvocationOnMock invocation) {
        return invocation.getArgument(0);
    }

    @BeforeEach
    void setUp() {
        studentList.addAll(Arrays.asList(
                new Student("James", "Bond", "bond.james@gmail.com"),
                new Student("Ralph", "Cruz", "cruz.ralph@gmail.com"),
                new Student("Claire", "Gram", "gram.claire@gmail.com")));
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/student/v1/hello").characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World"));
    }

    @Test
    void helloWithParameter() throws Exception {
        mockMvc.perform(get("/student/v1/hello?name=James").characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, James"));
    }

    @Test
    void getStudents() throws Exception {
        given(studentDTO.getStudentResponse().getStudentList()).willReturn(studentList);
        MvcResult mvcResult = mockMvc.perform(get("/student/v1/all"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void saveStudent() throws Exception {
        given(studentService.save(any(Student.class))).willAnswer(StudentControllerTest::answer);
        mockMvc.perform(post("/student/v1/save")
                .content(new ObjectMapper().writeValueAsString(studentList.get(0)))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(studentList.get(0).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(studentList.get(0).getLastName())))
                .andExpect(jsonPath("$.email", is(studentList.get(0).getEmail())));
    }

    @Test
    void updateStudent() throws Exception {
        given(studentService.save(any(Student.class))).willAnswer(StudentControllerTest::answer);
        mockMvc.perform(patch("/student/v1/patch")
                .content(new ObjectMapper().writeValueAsString(studentList.get(0)))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(studentList.get(0).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(studentList.get(0).getLastName())))
                .andExpect(jsonPath("$.email", is(studentList.get(0).getEmail())));
    }
}