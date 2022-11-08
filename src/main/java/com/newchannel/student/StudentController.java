package com.newchannel.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService, StudentCardService studentCardService) {
        this.studentService = studentService;
    }

    @GetMapping(path ="all")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();


    }
    @GetMapping(path = "{email}")
    public Optional<Student> findStudentByStudentEmail(@PathVariable("email") String email) {
        return studentService.findStudentByStudentEmail(email);
    }
    @PostMapping(path = "student" )
    public ResponseEntity<Student> addNewStudent(@Valid @RequestBody Student student) {
        if (student == null) {
                throw new IllegalArgumentException("Student is empty");
        } else {
                studentService.addNewStudent(student);
                return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(path = "delete/{email}")
    public ResponseEntity<String> deleteStudent(@PathVariable("email") String email) {
        if (studentService.findStudentByStudentEmail(email).isPresent()){
            studentService.removeStudent(email);
            return new ResponseEntity<>(email, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(email, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{email}")
    public ResponseEntity<String> updateStudent(@PathVariable("email") String email,
                                                @RequestParam(name = "first_name", required = false) String firstName,
                                                @RequestParam(name = "last_name", required = false) String lastName,
                                                @RequestParam(name = "email", required = false) String updatedEmail,
                                                @RequestParam(name = "date_of_birth", required = false) Date date) {

        if (studentService.findStudentByStudentEmail(email).isPresent()) {
            studentService.updateStudent(email, firstName, lastName, updatedEmail, date);
        }
        return new ResponseEntity<>(email, HttpStatus.ACCEPTED);
    }

}
