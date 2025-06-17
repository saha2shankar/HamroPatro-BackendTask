package com.hamropatro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamropatro.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
