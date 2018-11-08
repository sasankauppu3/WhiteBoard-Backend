package com.example.webdevf18s1VeeraUppuserverjava.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevf18s1VeeraUppuserverjava.Models.ListWidget;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Widget;

public interface ListWidgetRepository extends WidgetBaseRepository<ListWidget>{
	@Query("SELECT widget FROM ListWidget widget WHERE widget.topic.id=:tid")
	public List<Widget> findtopicWidgets(@Param("tid") int tid);}