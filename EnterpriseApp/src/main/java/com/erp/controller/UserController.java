package com.erp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.dao.UserService;
import com.erp.model.User;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String buildersave(@RequestParam(name = "usrnme") String usrnme,
			@RequestParam(name = "phnnbr") String phnnbr, @RequestParam(name = "email") String email,
			@RequestParam(name = "adr") String adr, @RequestParam(name = "pswd") String pswd) {
		User user = new User();
		user.setUsrnme(usrnme);
		user.setPhnnbr(phnnbr);
		user.setEmail(email);
		user.setAdr(adr);
		user.setPswd(pswd);
		if (userService.saveUser(user))
			return "site.home";
		else
			return "site.login";
	}
}
