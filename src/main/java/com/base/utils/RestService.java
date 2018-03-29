package com.base.utils;

import org.springframework.web.client.RestTemplate;

public class RestService {
	
	
	
	public static String getRestResponse(String API) {
		RestTemplate restTemplate = new RestTemplate();
		String responseFromUrl = restTemplate.getForObject(API, String.class);
		return responseFromUrl;
	}

}
