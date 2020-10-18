package in.jk.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.jk.springboot.jwt.TokenError;
import in.jk.springboot.response.Response;

@CrossOrigin
@RestController
@RequestMapping("/errorapi")
public class ErrorController {
	
	  @Autowired
	 HttpServletRequest httpServletRequest;
	  
	  @RequestMapping(value = "/error", produces = "application/json", method = {RequestMethod.GET,RequestMethod.POST})
		public Response tokenError() {
	           
		    System.out.println("Error in token Authorization");
	        Response response = new Response();
	        TokenError tokenError = (TokenError) httpServletRequest.getAttribute("tokenError");
	        response.setResponse("500", "Token Error ", tokenError, "Error in Token");

			return response;

		}

}
