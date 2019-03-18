package mumsched.controller;
import mumsched.service.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mumsched.dao.UserDao;
import mumsched.model.Student;
import mumsched.model.User;
import java.util.*;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDAO;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> getAllUser(){
		return userDAO.findAll();
	}
	
	@RequestMapping(value="/hi",method=RequestMethod.GET)
	public String hello() {
        
		return "hello";
	}
}
