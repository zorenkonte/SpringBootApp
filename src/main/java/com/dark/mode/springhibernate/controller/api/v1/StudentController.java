package com.dark.mode.springhibernate.controller.api.v1;

import com.dark.mode.springhibernate.controller.response.StudentResponse;
import com.dark.mode.springhibernate.exception.ResourceNotFoundException;
import com.dark.mode.springhibernate.model.Student;
import com.dark.mode.springhibernate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

    private final StudentService studentService;
    private final StudentResponse studentResponse;

    @Autowired
    public StudentController(StudentService studentService, StudentResponse studentResponse) {
        this.studentService = studentService;
        this.studentResponse = studentResponse;
    }

    @GetMapping("/all")
    public StudentResponse students() {
        studentResponse.setStudentList(studentService.all());
        return studentResponse;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getCustomer(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    @GetMapping("/email/{email}")
    public Student getTopByEmailLike(@PathVariable String email) {
        String emailFormat = String.format("%%%s%%", email);
        return studentService.findTopByEmailLike(emailFormat);
    }

    @GetMapping("/lastname/{lastName}")
    public StudentResponse getByLastName(@PathVariable String lastName) {
        studentResponse.setStudentList(studentService.findByLastName(lastName));
        return studentResponse;
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
