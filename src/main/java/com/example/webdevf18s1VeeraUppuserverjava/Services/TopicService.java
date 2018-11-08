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
import com.example.webdevf18s1VeeraUppuserverjava.Models.Topic;
import com.example.webdevf18s1VeeraUppuserverjava.Models.User;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.TopicRepository;

@RestController
@CrossOrigin(origins= {conReactApp}, allowCredentials = "true")
public class TopicService {
	@Autowired
	LessonService lessonService;
	
	@Autowired
	TopicRepository tr;
	
	@GetMapping("/api/lesson/{lid}/topic")
	public List<Topic> findAllTopics(@PathVariable("lid") int lId) {
		Lesson l = lessonService.findLessonById(lId);
		if (l==null)
			return null;
		
		return l.getTopics();
	}
	
	

	@PostMapping("/api/lesson/{lid}/topic")
	public List<Topic> createTopic(@PathVariable("lid")int lId,@RequestBody Topic topic) {
		Lesson l = lessonService.findLessonById(lId);
		List<Topic> topics = l.getTopics();
		topics.add(topic);
		l.setTopics(topics);
		
		topic.setLesson(l);
		tr.save(topic);
		lessonService.updateLesson(lId, l);
		
		return topics;
	
	}

	@GetMapping("/api/topic/{tid}")
	public Topic findTopicById(@PathVariable("tid")int tId) {
		return tr.findById(tId).orElse(null);
	}
	
	@PutMapping("/api/topic/{tid}")
	public Topic updateTopic(@PathVariable("tid")int tId, @RequestBody Topic topic) {
		
		Topic oldTop=tr.findById(tId).orElse(null);
		if (oldTop == null)
			return null;
		
		topic.setId(oldTop.getId());
		topic.setLesson(oldTop.getLesson());
		topic.setWidgets(oldTop.getWidgets());
		return tr.save(topic);
	}
	
	@DeleteMapping("/api/topic/{tid}")
	public void deleteTopic(@PathVariable("tid")int tId) {
		
		tr.deleteById(tId);

	}
}