package com.aakruth.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakruth.dao.RoleService;
import com.aakruth.dao.UserService;
import com.aakruth.model.Dealer;
import com.aakruth.model.RoleTbl;
import com.aakruth.model.UsrTbl;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/user/onLoad", method = RequestMethod.GET)
	public DataTablesOutput<RoleTbl> getUsers(@Valid DataTablesInput input) {
		return userService.findAll(input);
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.GET)
	public String builderSave(@RequestParam(name = "usrId") Integer usrId, @RequestParam(name = "usrnme") String usrnme,
			@RequestParam(name = "phnnbr") String phnnbr, @RequestParam(name = "email") String email,
			@RequestParam(name = "adr") String adr, @RequestParam(name = "roleSelect") Integer roleSelect) {
		UsrTbl user;
		RoleTbl role;
		if (usrId != 0) {
			user = userService.findOne(usrId);
			role = roleService.findByUsrTbl(user);
		} else {
			user = new UsrTbl();
			user.setPswd("Aakruth@123");
			user.setSta('L');
			user.setStrdte(new Date());
			user.setEnddte(new Date("01/01/9999"));
			role = new RoleTbl();
			role.setUsrTbl(user);
		}
		user.setUsrnme(usrnme);
		user.setEmail(email);
		user.setPhnnbr(phnnbr);
		user.setAdr(adr);
		switch (roleSelect) {
		case 1: {
			role.setRole("USER");
			break;
		}
		case 2: {
			role.setRole("ACCOUNTANT");
			break;
		}
		case 3: {
			role.setRole("ADMIN");
			break;
		}
		default: {
			role.setRole("USER");
		}

		}
		if (userService.saveUser(user)) {
			roleService.save(role);
			return "true";
		} else
			return "false";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public String userDelete(@RequestParam(name = "usrId") Integer usrId) {
		logger.info("User delete :" + usrId);
		if (userService.delete(usrId))
			return "true";
		else
			return "false";
	}
	@RequestMapping(value = "/user/reset", method = RequestMethod.GET)
	public String userReset(@RequestParam(name = "usrId") Integer usrId) {
		logger.info("User password reset :" + usrId);
		if (userService.reset(usrId))
			return "true";
		else
			return "false";
	}
	@RequestMapping(value = "/user/passChange", method = RequestMethod.GET)
	public String userPassChange(@RequestParam(name = "inputMail") String inputEmail) {
		logger.info("User change pass :" + inputEmail);
		return "true";
	}
}
