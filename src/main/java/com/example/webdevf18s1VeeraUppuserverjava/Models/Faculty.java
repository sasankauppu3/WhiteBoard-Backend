package com.example.webdevf18s1VeeraUppuserverjava.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Faculty extends User{

	@OneToMany(mappedBy="faculty",cascade = {CascadeType.ALL})
	private List<Section> sections = new ArrayList<Section>();
	
	@OneToMany(mappedBy="faculty",cascade = {CascadeType.ALL})
	private List<Course> courses = new ArrayList<Course>();
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	

}
