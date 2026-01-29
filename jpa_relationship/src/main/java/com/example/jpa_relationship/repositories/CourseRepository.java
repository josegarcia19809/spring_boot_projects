package com.example.jpa_relationship.repositories;

import com.example.jpa_relationship.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
