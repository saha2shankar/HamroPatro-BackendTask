package com.hamropatro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamropatro.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByClassNumber_ClassName(String className);

	List<Student> findBySection(String section);

	List<Student> findByClassNumber_TeachingAssignments_Teacher_Id(Integer teacherId);

}
