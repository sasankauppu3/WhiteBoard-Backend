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
import com.example.webdevf18s1VeeraUppuserverjava.Models.Lesson;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Module;
import com.example.webdevf18s1VeeraUppuserverjava.Models.User;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.LessonRepository;


@RestController
@CrossOrigin(origins= {conReactApp}, allowCredentials = "true")
public class LessonService {
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	LessonRepository lr;
	
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessons(@PathVariable("mid") int mId) {
		Module mod = moduleService.findModuleById(mId);
		if (mod==null)
			return null;
		
		return mod.getLessons();
	}
	
	

	@PostMapping("/api/module/{mid}/lesson")
	public List<Lesson> createLesson(@PathVariable("mid")int mId,@RequestBody Lesson lesson) {
		Module mod = moduleService.findModuleById(mId);
		List<Lesson> lessons = mod.getLessons();
		lessons.add(lesson);
		mod.setLessons(lessons);
		
		lesson.setModule(mod);
		lr.save(lesson);
		moduleService.updateModule(mId, mod);
		
		return lessons;
	}

	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid")int lId) {
		/*List<Course> courses = courseService.findAllCourses(session);
		
		for(Course course: courses) {
			List<Module> modules = moduleService.findAllModules(course.getId());
			for(Module mod:modules)
			{
				List<Lesson> lessons = mod.getLessons();
				for (Lesson l:lessons)
				{
					if (l.getId()==lId)
						return l;
				}
			}
		}
		return null;*/
		
		return lr.findById(lId).orElse(null);
	}
	
	@PutMapping("/api/lesson/{lId}")
	public Lesson updateLesson(@PathVariable("lId")int lId, @RequestBody Lesson lesson) {
		/*List<Course> courses = courseService.findAllCourses(session);
		Boolean fl=false;
		for(Course course: courses) {
			List<Module> mods=course.getModules();
			
			for(Module mod:mods)
			{
				List<Lesson> lessons = mod.getLessons();
				for (int i=0;i<lessons.size();i++)
				{
					if (lessons.get(i).getId()==lId)
						{
						lessons.set(i, lesson);
						fl=true;
						}
				}
				mod.setLessons(lessons);
			}
			course.setModules(mods);
		}
		if (!fl)
			return null;
		return lesson;*/
		
		Lesson oldLess=lr.findById(lId).orElse(null);
		if (oldLess == null)
			return null;
		
		lesson.setId(oldLess.getId());
		lesson.setModule(oldLess.getModule());
		lesson.setTopics(oldLess.getTopics());
		return lr.save(lesson);
	}
	
	@DeleteMapping("/api/lesson/{lid}")
	public void deleteLesson(@PathVariable("lid")int lId) {
		/*List<Course> courses = courseService.findAllCourses(session);
		
		for(Course course: courses) {
			List<Module> mods=course.getModules();
			
			for(Module mod:mods)
			{
				List<Lesson> lessons = mod.getLessons();
				for (int i=0;i<lessons.size();i++)
				{
					if (lessons.get(i).getId()==lId)
						lessons.remove(i);
				}
				mod.setLessons(lessons);
			}
			course.setModules(mods);
		}
		*/
		lr.deleteById(lId);

	}
}