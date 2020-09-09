package com.dark.mode.springhibernate.controller.api.v1;

import com.dark.mode.springhibernate.controller.response.StudentResponse;
import com.dark.mode.springhibernate.exception.ResourceNotFoundException;
import com.dark.mode.springhibernate.model.Student;
import com.dark.mode.springhibernate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Student getStudentById(@PathVariable Integer id) throws ResourceNotFoundException {
        return studentService.getStudent(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourceNotFoundException, AssertionError {
        Optional<Student> student = studentService.getStudent(id);
        if (student.isPresent()) {
            studentService.delete(student.get());
            return new ResponseEntity<>(this.responseMessage(student.get()), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Student", "id", id);
        }
    }

    @PatchMapping("/patch")
    public Student update(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/save")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    private String responseMessage(Student student) {
        return String.format("Student: %s with id %s successfully deleted!", this.fullName(student), student.getId());
    }

    private String fullName(Student student) {
        return String.format("%s, %s", student.getLastName(), student.getFirstName());
    }

}
