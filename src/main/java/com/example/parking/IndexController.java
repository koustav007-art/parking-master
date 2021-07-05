package com.example.parking;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String home(Map<String, Object> model, Principal principal) {
		model.put("message", "PARKING LOT");
		String username = principal.getName();
		if (username.equals("admin")) {
			return "welcome";
		} else {
			return "welcomeuser";
		}
		
	}

}