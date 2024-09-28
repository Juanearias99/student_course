package com.example.student_course.service;

import com.example.student_course.dto.CourseDto;
import com.example.student_course.dto.StudentDto;
import com.example.student_course.entity.Course;
import com.example.student_course.entity.Student;
import com.example.student_course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired //Singleton backwards for just one commentService instance
    private CourseRepository courseRepository;


    //This method refers to courseService.findAll() method. Brings out every Course stored in database table course as a List of courses
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
    //This method finds a specific course searching by id
    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }
    //This method saves a new course in database table course

    public Course save(CourseDto courseDto) {
        Course course = new Course();
        course.setName(course.getName());
        course.setDescription(course.getDescription());
        course.setCredits(course.getCredits());
        return courseRepository.save(course);
    }
    //This method updates a new course in database table course
    public Course updateCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setCredits(courseDto.getCredits());
        return courseRepository.save(course);
    }
    //This method deletes a specific course by using its id
    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }
}
