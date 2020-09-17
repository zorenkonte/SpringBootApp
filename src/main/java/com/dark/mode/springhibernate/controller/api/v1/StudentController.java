package com.dark.mode.springhibernate.controller.api.v1;

import com.dark.mode.springhibernate.controller.response.StudentResponse;
import com.dark.mode.springhibernate.exception.ResourceNotFoundException;
import com.dark.mode.springhibernate.model.Student;
import com.dark.mode.springhibernate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
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
    public Student getStudentById(@PathVariable Integer id) throws ResourceNotFoundException {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    @GetMapping("/email/{email}")
    public Student getTopByEmailLike(@PathVariable String email) throws ResourceNotFoundException {
        return studentService.findTopByEmailLike(emailFormat(email))
                .orElseThrow(() -> new ResourceNotFoundException("Student", "email", email));
    }

    @GetMapping("/lastname/{lastName}")
    public StudentResponse getByLastName(@PathVariable String lastName) {
        studentResponse.setStudentList(studentService.findByLastName(lastName));
        return studentResponse;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        Student student = this.getStudentById(id);
        studentService.delete(student);
        return new ResponseEntity<>(responseMessage(student), HttpStatus.OK);
    }

    @PatchMapping("/patch")
    public Student update(@RequestBody Student student) throws ResourceNotFoundException {
        if (student.getId() == null) {
            throw new ResourceNotFoundException("Student", "id", "null");
        }
        return studentService.update(student);
    }

    @PostMapping("/save")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    private String responseMessage(Student student) {
        return String.format("Student: %s with id %s successfully deleted!", fullName(student), student.getId());
    }

    private String fullName(Student student) {
        return String.format("%s, %s", student.getLastName(), student.getFirstName());
    }

    private String emailFormat(String email) {
        return String.format("%%%s%%", email);
    }

}
