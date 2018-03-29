/*
package com.creyate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creyate.bean.Customer;
import com.creyate.repository.CustomerRepository;

@Controller
public class MongoController {
	
	      // The Environment object will be used to read parameters from the 
		  // application.properties configuration file
		  @Autowired
		  private Environment env;
		  
		  @Autowired
		  private CustomerRepository customerRepository;
		  
		  @RequestMapping("/mongo")
		  @ResponseBody
		  public String welcome() {
				 return "MONGO CONTROLLER WORKS FINE";
			}
		  
		  @RequestMapping("/addCustomer")
		  @ResponseBody
		  public String addCustomer(String first, String last) {
			  customerRepository.save(new Customer(first, last));
				 return "CUSTOMER ADDED SUCCESSFULLY";
			}
		  
		  @RequestMapping("/findAllCustomer")
		  @ResponseBody
		  public String findAllCustomer() {
			  List<String> names = new ArrayList<String>();
		
				for (Customer customer : customerRepository.findAll()) {
					  names.add(customer.getFirstName()+" "+customer.getLastName());
					}
			
				 return names.toString();
			}
		  
		  

}
*/
