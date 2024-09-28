package com.example.student_course.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private long id;
    @Size(min = 10, max = 50 )
    private String name;
    @Size(min = 20, max = 60)

    private String description;

    private int credits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
