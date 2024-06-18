package com.crud.thymleaf.repository;

import com.crud.thymleaf.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
