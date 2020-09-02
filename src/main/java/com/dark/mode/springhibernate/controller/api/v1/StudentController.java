package com.dark.mode.springhibernate.controller.api.v1;

import com.dark.mode.springhibernate.controller.response.StudentResponse;
import com.dark.mode.springhibernate.dto.StudentDTO;
import com.dark.mode.springhibernate.exception.ResourceNotFoundException;
import com.dark.mode.springhibernate.model.Student;
import com.dark.mode.springhibernate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

    private final StudentService studentService;
    private final StudentDTO studentDTO;

    @Autowired
    public StudentController(StudentService studentService, StudentDTO studentDTO) {
        this.studentService = studentService;
        this.studentDTO = studentDTO;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }

    @GetMapping("/all")
    public StudentResponse students() {
        studentDTO.setStudentList(studentService.all());
        return studentDTO.getStudentResponse();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getCustomer(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    @GetMapping("/lastname/{lastName}")
    public StudentResponse getByLastName(@PathVariable String lastName) {
        studentDTO.setStudentList(studentService.findByLastName(lastName));
        return studentDTO.getStudentResponse();
    }

    @PatchMapping("/patch")
    public Student update(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/save")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

}
