package com.example.webdevf18s1VeeraUppuserverjava.Services;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.webdevf18s1VeeraUppuserverjava.Models.Widget;
import com.example.webdevf18s1VeeraUppuserverjava.Repositories.UserRepository;

@RestController
@CrossOrigin(origins= {conReactApp}, allowCredentials = "true")
public class UserService {

	@Autowired
	UserRepository ur;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) ur.findAll();
	}

	@GetMapping("/api/user/{id}")
	public User findUserById(@PathVariable("id") int userId) {
		return ur.findById(userId).orElse(null);
	}

	@PostMapping("/api/user")
	public List<User> createUser(@RequestBody User user) {
		List<User> users = findAllUsers();
		
		for (User u : users) {
			  if( u.getUsername().equals(user.getUsername())) {
			    return null;
			  }
		}
		users.add(user);
		return users;
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user,HttpSession session) {
		List<User> users = findAllUsers();
		for (User u : users) {
			  if( u.getUsername().equals(user.getUsername())) {
			    return null;
			  }
		}
		
		session.setAttribute("currentUser", user);
		users.add(user);
		ur.save(user);
		return user;
	}
	
	@PostMapping("/api/profile")
	public User profile(HttpSession session) {
		return (User)session.getAttribute("currentUser");
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user,HttpSession session) {
		
		User oldUser = ur.findById(user.getId()).orElse(null);
		if (oldUser==null)
			return null;
		
		user.setPassword(oldUser.getPassword());
		ur.save(user);
		return user;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User credentials,HttpSession session) {
		List<User> users = findAllUsers();
		 for (User user : users) {
		  if( user.getUsername().equals(credentials.getUsername()) && user.getPassword().equals(credentials.getPassword())) {
		    session.setAttribute("currentUser", user);
		    return user;
		  }
		 }
		 return null;
		
	}

}
