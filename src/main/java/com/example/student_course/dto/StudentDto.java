package com.example.student_course.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

;

public class StudentDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private long id;

    @Size(min = 10, max = 50 )
    private String name;
    private int age;

    @Email(message = "Ingrese bien el correo")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
