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
import com.example.webdevf18s1VeeraUppuserverjava.Models.Module;
import com.example.webdevf18s1VeeraUppuserverjava.Models.User;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.ModuleRepository;


@RestController
@CrossOrigin(origins= {conReactApp}, allowCredentials = "true")
public class ModuleService {
	@Autowired
	CourseService courseService;
	@Autowired
	ModuleRepository mr;
	
	@GetMapping("/api/course/{cid}/module")
	public List<Module> findAllModules(@PathVariable("cid") int courseId) {
		Course course = courseService.findCourseById(courseId);
		if (course==null)
			return null;
		
		return course.getModules();
	}
	

	@PostMapping("/api/course/{cid}/module")
	public List<Module> createModule(@PathVariable("cid")int courseId,@RequestBody Module module) {
		Course course = courseService.findCourseById(courseId);
		module.setCourse(course);
		mr.save(module);
		
		List<Module> mods = course.getModules();
		mods.add(module);
		course.setModules(mods);

		courseService.updateCourse(courseId,course);
		
		return mods;
	}

	@GetMapping("/api/module/{mid}")
	public Module findModuleById(@PathVariable("mid")int mId) {
		return mr.findById(mId).orElse(null);
	}
	
	@PutMapping("/api/module/{mid}")
	public Module updateModule(@PathVariable("mid")int mId, @RequestBody Module module) {
		Module oldMod=mr.findById(mId).orElse(null);
		if (oldMod == null)
			return null;
		
		module.setId(oldMod.getId());
		module.setCourse(oldMod.getCourse());
		module.setLessons(oldMod.getLessons());
		return mr.save(module);
	}
	
	@DeleteMapping("/api/module/{mid}")
	public void deleteModule(@PathVariable("mid")int mId) {
		mr.deleteById(mId);

	}
}