package com.example.webdevf18s1VeeraUppuserverjava.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevf18s1VeeraUppuserverjava.Models.ImageWidget;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Widget;

public interface ImageWidgetRepository extends WidgetBaseRepository<ImageWidget>{
	@Query("SELECT widget FROM ImageWidget widget WHERE widget.topic.id=:tid")
	public List<Widget> findtopicWidgets(@Param("tid") int tid);
}