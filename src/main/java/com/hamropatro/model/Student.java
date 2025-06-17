package com.hamropatro.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "students")
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String section;
	@Column(name = "roll_number", nullable = false, unique = true)
	private Integer rollNumber;
	@Column(name = "contact_details")
	private String contactDetails;
	@ManyToOne
	@JoinColumn(name = "class_section_id") // This column will be the foreign key in the Student table
	private ClassSection classNumber;
}
