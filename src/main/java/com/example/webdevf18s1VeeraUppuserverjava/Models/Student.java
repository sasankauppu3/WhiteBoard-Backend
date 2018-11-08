package com.example.webdevf18s1VeeraUppuserverjava.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Student extends User{

	@OneToMany(mappedBy="student",cascade = {CascadeType.ALL})
	private List<Enrollment> enrollments;

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
}
