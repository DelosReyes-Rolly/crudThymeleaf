package com.crud.thymeleaf.repository;

import com.crud.thymeleaf.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
