package com.objis.spring.demodatabase.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping(value = "/error")
	public String error(HttpServletRequest request, HttpServletResponse response) {
		int errorCode = response.getStatus(); 
		
		// Erreurs ayant des pages spécifiques
		if(errorCode == 404 || errorCode == 500)
			return Integer.toString(errorCode);
		else
			return "error";
	}
}
