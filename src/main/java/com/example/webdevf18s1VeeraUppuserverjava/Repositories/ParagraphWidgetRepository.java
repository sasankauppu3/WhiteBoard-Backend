package com.example.webdevf18s1VeeraUppuserverjava.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevf18s1VeeraUppuserverjava.Models.ParagraphWidget;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Widget;

public interface ParagraphWidgetRepository extends WidgetBaseRepository<ParagraphWidget>{
	@Query("SELECT widget FROM ParagraphWidget widget WHERE widget.topic.id=:tid")
	public List<Widget> findtopicWidgets(@Param("tid") int tid);}