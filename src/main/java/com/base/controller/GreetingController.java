package com.base.controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();



	@Value("${application.message:Hello World}")
	private String message = "Hello World";






	@RequestMapping("/health")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome  gs-rest-service "+message;
	}

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="natu") String name) {
		//String userid = ApplicationProperties.getProperty("database.username");

		/*	CityModel cm = new CityModel();
    	System.out.println("Starting to print");
    	      List<City> list = cm.getAllCity();
    	System.out.println(list);*/
		return new Greeting(counter.incrementAndGet(),
				String.format(template,  1));
	}



	@RequestMapping("/city")
	@ResponseBody 
	public String getCityByID() {

		return "The user id is: " + "DELHI";
	}






}
