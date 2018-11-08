package com.example.webdevf18s1VeeraUppuserverjava.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import com.fasterxml.jackson.annotation.JsonIgnore;


@JsonTypeInfo(use=com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME,
include=As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(name="Heading",value=HeadingWidget.class),
	@Type(name="Paragraph",value=ParagraphWidget.class),
	@Type(name="List",value=ListWidget.class),
	@Type(name="Image",value=ImageWidget.class),
	@Type(name="Link",value=LinkWidget.class)})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@ManyToOne
	@JsonIgnore
	private Topic topic;
	
	public Widget() {}
	public Widget(String title) {
		this.title = title;
	}
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
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}