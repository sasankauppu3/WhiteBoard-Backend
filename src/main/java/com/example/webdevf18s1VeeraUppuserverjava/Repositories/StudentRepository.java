
package com.example.webdevf18s1VeeraUppuserverjava.Repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webdevf18s1VeeraUppuserverjava.Models.Student;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student>{}