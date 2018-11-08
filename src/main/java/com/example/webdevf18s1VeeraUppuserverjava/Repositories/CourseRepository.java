package com.example.webdevf18s1VeeraUppuserverjava.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevf18s1VeeraUppuserverjava.Models.Course;

public interface CourseRepository extends CrudRepository<Course,Integer>{

	@Query("SELECT course FROM Course course WHERE course.faculty.id=:uid")
	public List<Course> findUserCourses(@Param("uid") int uid);
}