/*
package com.creyate.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.creyate.bean.User;
import com.creyate.repository.UserRepository;
import com.creyate.utils.RestService;



@RestController
public class UserController {


	private static String API = null;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private Environment env;

	@RequestMapping("/createuser")
	@ResponseBody
	public String create(String email, String name) {
		String userId = "";
		try {
			User user = new User(email, name);
			userRepository.save(user);
			userId = String.valueOf(user.getId());
		}
		catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created with id = " + userId;
	}

	*/
/**
	 * GET /delete  --> Delete the user having the passed id.
	 *//*

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			User user = new User(id);
			userRepository.delete(user);
		}
		catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	*/
/**
	 * GET /get-by-email  --> Return the id for the user having the passed
	 * email.
	 *//*

	@RequestMapping("/get-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String userId = "";
		try {
			User user = userRepository.findByEmail(email);
			userId = String.valueOf(user.getId());
		}
		catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	@RequestMapping("/get-by-id")
	@ResponseBody
	public String getById(Long id) {
		String userId = "";
		try {
			User user = userRepository.findById(id);
			userId = String.valueOf(user.getName());
		}
		catch (Exception ex) {
			//return "User not found";
			return "User Not found";
		}
		return "The user id is: " + userId;
	}


	@RequestMapping("/findalluser")
	@ResponseBody
	public String findAll() {
		
		HashMap<Long, String> map = new HashMap<Long,String>();
		try {
			Iterable<User> user = userRepository.findAll();
			for (User usr : user) {
				map.put(usr.getId(),usr.getName() +","+usr.getEmail());
			}

		}
		catch (Exception ex) {
			//return "User not found";
			return ex.getMessage();
		}
		return "ALL USER ARE " + map;
	}


	@RequestMapping("/get-by-name")
	@ResponseBody
	public String getByName(String name) {
		String username = "";
		try {
			User user = userRepository.findByName(name);
			username = String.valueOf(user.getName());
		}

		catch (Exception ex) {
			String s =  "User not found";
			return s+ ex.getMessage();
		}
		return "The user name is: " + username;
	}
	
	
	@RequestMapping("/get-by-name/{name}")
	@ResponseBody
	public String getByNames(@PathVariable(value="name") String name) {
		String username = "";
		try {
			User user = userRepository.findByName(name);
			username = String.valueOf(user.getName());
		}

		catch (Exception ex) {
			String s =  "User not found";
			return s+ ex.getMessage();
		}
		return "The user name is: " + username;
	}

	*/
/**
	 * GET /update  --> Update the email and the name for the user in the 
	 * database having the passed id.
	 *//*

	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(long id, String email, String name) {
		try {
			User user = userRepository.findOne(id);
			user.setEmail(email);
			user.setName(name);
			userRepository.save(user);
		}
		catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}
	
	
	*/
/**
	 * GET /update  --> Update the email and the name for the user in the 
	 * database having the passed id.
	 *//*

	@RequestMapping("/requesturl")
	@ResponseBody
	public String requestMapping( String url) {
		API = env.getProperty(url);
		String responseFromUrl = RestService.getRestResponse(API);
		return responseFromUrl;
	}

	


}
*/
