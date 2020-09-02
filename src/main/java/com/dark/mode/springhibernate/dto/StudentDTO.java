package com.dark.mode.springhibernate.dto;

import com.dark.mode.springhibernate.controller.response.StudentResponse;
import com.dark.mode.springhibernate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDTO {

    private final StudentResponse studentResponse;

    @Autowired
    public StudentDTO(StudentResponse studentResponse) {
        this.studentResponse = studentResponse;
    }

    public StudentResponse getStudentResponse() {
        return studentResponse;
    }

    public void setStudentList(Iterable<Student> studentResponse) {
        this.studentResponse.setStudentList(studentResponse);
    }
}
