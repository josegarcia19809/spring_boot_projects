package com.example.jpa_relationship.repositories;

import com.example.jpa_relationship.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("""
                select distinct s
                from Student s
                left join fetch s.courses
            """)
    List<Student> findAllWithCourses();

    @Query("""
                select distinct s
                from Student s
                left join fetch s.courses
                            where s.id=?1
            """)
    Optional<Student> findOneWithCourses(Long id);
}
