package com.hamropatro.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String contactDetails;
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TeachingAssignment> assignments = new ArrayList<>();
}
