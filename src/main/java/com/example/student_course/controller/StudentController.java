package com.example.student_course.controller;

import com.example.student_course.dto.StudentDto;
import com.example.student_course.entity.Student;
import com.example.student_course.service.CourseService;
import com.example.student_course.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Students")
public class StudentController {

    @Autowired //Singleton backwards for just one studentService instance
    private StudentService studentService;

    //This method refers to studentService.findAll() method. Brings out every student stored in database table student as a List of students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }


    //This method refers to studentService.findById() method. Finds a specific student searching by id
    //If the student is found, maps the ResponseEntity and returns a 200 OK Status.
    //If there is not a student identified by that id, returns 404 Not Found Status
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //This method refers to studentService.save() method. Saves a new student in database table student
    @PostMapping
    public Student createStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    //This method refers to studentService.findById() and studentService.save() methods. Finds a specific student searching by id and updates it
    //If the student is found, sets the attributes to the student in edition, saves to update and returns a 200 OK Status.
    //If there is not a student identified by that id, returns 404 Not Found Status
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student) {
        Optional<Student> findStudent = studentService.findById(id);
        if (findStudent.isPresent()) {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(findStudent.get().getId());
            studentDto.setName(student.getName());
            studentDto.setAge(student.getAge());
            studentDto.setEmail(student.getEmail());
            return ResponseEntity.ok(studentService.update(studentDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //This method refers to studentService.findById() and studentService.deleteById() methods. Finds a specific student searching by id and deletes it
    //If the student is found, deletes it.
    //If there is not a student identified by that id, returns 404 Not Found Status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        if (studentService.findById(id).isPresent()) {
            studentService.deletebyId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
