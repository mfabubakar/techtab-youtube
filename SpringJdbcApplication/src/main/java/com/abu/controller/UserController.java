package com.abu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.abu.service.UserService;

import abu.dao.model.User;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String Hello() {
		return "Hello from SpringJdbc App";
	}

	@GetMapping("/list")
	
	public ModelAndView list() {

		ModelAndView mv = new ModelAndView("user/user_page");

		List<User> list = userService.listAllUsers();
		mv.addObject("listUser", list);
		return mv;

	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView model = new ModelAndView("user/user_form");

		User user = new User();
		model.addObject("userForm", user);

		return model;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	
	public ModelAndView update(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("user/user_form");

		User user = userService.findUserById(id);
		model.addObject("userForm", user);

		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("userForm") User user) {
		if (user != null && user.getId() != 0) {
			userService.updateUser(user);
		} else {
			userService.addUser(user);
		}

		return new ModelAndView("redirect:/user/list");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		userService.deleteUser(id);

		return new ModelAndView("redirect:/user/list");
	}

}
