package com.hamropatro.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "class_sections")
@Data
public class ClassSection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String className;
	

	@OneToMany(mappedBy = "classSection", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TeachingAssignment> teachingAssignments = new ArrayList<>();

	public List<TeachingAssignment> getTeachingAssignments() {
		return teachingAssignments;
	}

	public void setTeachingAssignments(List<TeachingAssignment> teachingAssignments) {
		this.teachingAssignments = teachingAssignments;
	}

	@OneToMany(mappedBy = "classNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();  //  'mappedBy' matches field in Student

}
