package com.example.student_course.service;

import com.example.student_course.dto.StudentDto;
import com.example.student_course.entity.Student;
import com.example.student_course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    //This method refers to courseService.findAll() method. Brings out every Course stored in database table course as a List of courses
    public List<Student> findAll() {
        return studentRepository.findAll();

    }

    //This method finds a specific course searching by id
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }

    //This method saves a new course in database table course
    public Student save(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        return studentRepository.save(student);
    }

    //This method updates a new course in database table course
    public Student update(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        return studentRepository.save(student);
    }

    //This method deletes a specific course by using its id
    public void deletebyId(long id){
        studentRepository.deleteById(id);
    }
}
