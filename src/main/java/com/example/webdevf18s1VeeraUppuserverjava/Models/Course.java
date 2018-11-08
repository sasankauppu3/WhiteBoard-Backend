package com.example.webdevf18s1VeeraUppuserverjava.Models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@OneToMany(mappedBy="course",cascade = {CascadeType.ALL})
	private List<Module> modules = new ArrayList<Module>();
	
	@OneToMany(mappedBy="course",cascade = {CascadeType.ALL})
	private List<Section> sections = new ArrayList<Section>();
	
	@JsonIgnore
	@ManyToOne
	private Faculty faculty;
	
	public Course(String title) {
		this.title = title;
	}
	
	public Course(int i, String string) {
		id = i; title = string;
	}
	public Course() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty=faculty;
	}
	  
}