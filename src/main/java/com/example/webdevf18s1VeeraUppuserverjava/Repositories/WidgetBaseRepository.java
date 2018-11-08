package com.example.webdevf18s1VeeraUppuserverjava.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.webdevf18s1VeeraUppuserverjava.Models.User;
import com.example.webdevf18s1VeeraUppuserverjava.Models.Widget;

@NoRepositoryBean
public interface WidgetBaseRepository <T extends Widget> extends CrudRepository<T,Integer>{

}