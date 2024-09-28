package com.example.student_course.controller;

import com.example.student_course.dto.CourseDto;
import com.example.student_course.dto.StudentDto;
import com.example.student_course.entity.Course;
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
@RequestMapping("/api/Courses")
public class CourseController {


    @Autowired //Singleton backwards for just one courseService instance
    private CourseService courseService;

    //This method refers to courseService.findAll() method. Brings out every course stored in database table student as a List of courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }


    //This method refers to courseService.findById() method. Finds a specific course searching by id
    //If the course is found, maps the ResponseEntity and returns a 200 OK Status.
    //If there is not a course identified by that id, returns 404 Not Found Status
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable long id) {
        Optional<Course> course = courseService.findById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //This method refers to courseService.save() method. Saves a new course in database table course
    @PostMapping
    public Course createStudent(@Valid @RequestBody CourseDto courseDto) {
        return courseService.save(courseDto);
    }

    //This method refers to courseService.findById() and courseService.save() methods. Finds a specific course searching by id and updates it
    //If the course is found, sets the attributes to the course in edition, saves to update and returns a 200 OK Status.
    //If there is not a course identified by that id, returns 404 Not Found Status
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable long id, @RequestBody Course course) {
        Optional<Course> findCourse = courseService.findById(id);
        if (findCourse.isPresent()) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(findCourse.get().getId());
            courseDto.setName(course.getName());
            courseDto.setDescription(course.getDescription());
            courseDto.setCredits(course.getCredits());
            return ResponseEntity.ok(courseService.updateCourse(courseDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //This method refers to courseService.findById() and courseService.deleteById() methods. Finds a specific course searching by id and deletes it
    //If the course is found, deletes it.
    //If there is not a course identified by that id, returns 404 Not Found Status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable long id) {
        if (courseService.findById(id).isPresent()) {
            courseService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
