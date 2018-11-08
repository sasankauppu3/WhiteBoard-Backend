package com.example.webdevf18s1VeeraUppuserverjava.Services;

import static com.example.webdevf18s1VeeraUppuserverjava.Models.Constants.conReactApp;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevf18s1VeeraUppuserverjava.Models.HeadingWidget;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Topic;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Widget;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.HeadingWidgetRepository;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.WidgetRepository;

@RestController
@CrossOrigin(origins= {conReactApp}, allowCredentials = "true")
public class HeadingWidgetService {

	@Autowired
	TopicService topicService;
	
	@Autowired
	HeadingWidgetRepository wr;

	@PostMapping("/api/topic/{tid}/heading/widget")
	public List<Widget> createWidget(@PathVariable("tid")int tid, HeadingWidget widget) {
		Topic t = topicService.findTopicById(tid);
		List<Widget> widgets=t.getWidgets();
		widgets.add(widget);
		t.setWidgets(widgets);
		
		widget.setTopic(t);
		wr.save(widget);
		topicService.updateTopic(tid,t);
		
		return widgets;
	}

	@GetMapping("/api/topic/{tid}/heading/widget")
	public List<Widget> findAllWidgets(@PathVariable("tid") int tid) {
		return wr.findtopicWidgets(tid);
	}
	

	@GetMapping("/api/heading/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid")int wid) {
		return wr.findById(wid).orElse(null);
	}
	
	@PutMapping("/api/heading/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid")int wid, @RequestBody HeadingWidget widget) {
		
		Widget oldw=wr.findById(wid).orElse(null);
		if (oldw == null)
			return null;
		
		widget.setId(oldw.getId());
		widget.setTopic(oldw.getTopic());
		return wr.save(widget);
	}
	
	@DeleteMapping("/api/heading/widget/{wid}")
	public void deleteWidget(@PathVariable("wid")int wid) {
		wr.deleteById(wid);
	}
	
}
