package in.jk.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.jk.springboot.request.UserRequest;
import in.jk.springboot.response.Response;
import in.jk.springboot.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/userapi")
public class UserController {

	@Autowired
	UserService userService;
    @Autowired
	HttpServletRequest httpServletRequest;

	@RequestMapping(value = "/findUserByUserId/{userId}", produces = "application/json", method = RequestMethod.GET)
	public Response findUserByUserId(@PathVariable Integer userId) {

		return userService.findUserByUserId(userId);

	}

	@RequestMapping(value = "/addUser", produces = "application/json", method = RequestMethod.POST)
	public Response addUser(@RequestBody UserRequest userRequest) {

		return userService.addUser(userRequest);

	}

	

}
