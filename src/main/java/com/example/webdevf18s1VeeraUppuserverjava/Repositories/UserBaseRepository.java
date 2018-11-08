package com.example.webdevf18s1VeeraUppuserverjava.Repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.webdevf18s1VeeraUppuserverjava.Models.User;

@NoRepositoryBean
public interface UserBaseRepository <T extends User> extends CrudRepository<T,Integer>{

}