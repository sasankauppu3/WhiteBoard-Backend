package com.example.webdevf18s1VeeraUppuserverjava.Services;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.example.webdevf18s1VeeraUppuserverjava.Models.Constants.conReactApp;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Course;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Faculty;
import com.example.webdevf18s1VeeraUppuserverjava.Models.User;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.CourseRepository;



@RestController
@CrossOrigin(origins= {conReactApp}, allowCredentials = "true")
public class CourseService {

	@Autowired
	UserService userService;
	
	@Autowired
	CourseRepository cr;
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses(HttpSession session) {
		User user = (User)session.getAttribute("currentUser");	
		return (List<Course>)cr.findUserCourses(user.getId());
	}

	@PostMapping("/api/course")
	public List<Course> createCourse(@RequestBody Course course, HttpSession session) {
		User user = (User)session.getAttribute("currentUser");	
		course.setFaculty((Faculty)user);
		
		cr.save(course);
		return (List<Course>)cr.findUserCourses(user.getId());
	}

	@GetMapping("/api/course/{cid}")
	public Course findCourseById(@PathVariable("cid")int cid) {
		
		return cr.findById(cid).orElse(null);
	}
	
	@PutMapping("/api/course/{cid}")
	public Course updateCourse(@PathVariable("cid")int cid, @RequestBody Course course) {
		
		Course oldCourse=cr.findById(cid).orElse(null);
		if (oldCourse == null)
			return null;
		
		course.setId(oldCourse.getId());
		return cr.save(course);
	}
	
	@DeleteMapping("/api/course/{cid}")
	public void deleteCourse(@PathVariable("cid")int courseId) {
		
		cr.deleteById(courseId);
	}
}
