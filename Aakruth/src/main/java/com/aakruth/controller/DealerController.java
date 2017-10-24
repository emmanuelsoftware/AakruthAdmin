package com.aakruth.controller;

import java.security.Principal;
import java.util.List;

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

import com.aakruth.dao.DealerService;
import com.aakruth.dao.UserService;
import com.aakruth.model.Dealer;
import com.aakruth.model.UsrTbl;
import com.fasterxml.jackson.annotation.JsonView;


@RestController  
public class DealerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	UserService userService;

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/builder/onLoad", method = RequestMethod.GET)
	public DataTablesOutput<Dealer> getBuilders(@Valid DataTablesInput input) {
		return dealerService.findAllBuilders(input);
	}
	
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/customer/onLoad", method = RequestMethod.GET)
	public DataTablesOutput<Dealer> getCustomers(@Valid DataTablesInput input) {
		return dealerService.findAllCustomers(input);
	}
	
	@RequestMapping(value = "/builder/list", method = RequestMethod.GET)
	public List<Dealer> getBuilderList() {
		return dealerService.findByStaAndType('B');
	}
	
	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public List<Dealer> getCustomerList() {
		return dealerService.findByStaAndType('C');
	}
	
	@RequestMapping(value = "/dealer/list", method = RequestMethod.GET)
	public List<Dealer> getDealerList() {
		return dealerService.findBySta();
	}
	
	
	@RequestMapping(value = "/dealer/edit", method = RequestMethod.GET)
	public String builderEdit(Principal principal,@RequestParam(name="bldId") Integer bldId,@RequestParam(name="bldnme") String bldnme, @RequestParam(name="poc") String poc,@RequestParam(name="phnnbr") String phnnbr,@RequestParam(name="email") String email, @RequestParam(name="adr") String adr) {
		Dealer builder = dealerService.findOne(bldId);
		logger.info(principal.getName()+" dealer edit before :"+builder);
		builder.setName(bldnme);
		builder.setPoc(poc);
		builder.setPhnnbr(phnnbr);
		builder.setEmail(email);
		builder.setAdr(adr);
		builder.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info("User dealer edit after :"+builder);
		if(dealerService.edit(builder))
		   return "true";
		else
		   return "false";
	}
	
	@RequestMapping(value = "/builder/save", method = RequestMethod.GET)
	public String builderSave(Principal principal,@RequestParam(name="bldnme") String bldnme, @RequestParam(name="poc") String poc,@RequestParam(name="phnnbr") String phnnbr,@RequestParam(name="email") String email, @RequestParam(name="adr") String adr) {
		Dealer builder = new Dealer();
		builder.setName(bldnme);
		builder.setPoc(poc);
		builder.setPhnnbr(phnnbr);
		builder.setEmail(email);
		builder.setAdr(adr);
		builder.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info(principal.getName()+" builder save :"+builder);
		if(dealerService.saveBuilder(builder))
		   return "true";
		else
		   return "false";
	}
	
	@RequestMapping(value = "/customer/save", method = RequestMethod.GET)
	public String customerSave(Principal principal,@RequestParam(name="bldnme") String bldnme, @RequestParam(name="poc") String poc,@RequestParam(name="phnnbr") String phnnbr,@RequestParam(name="email") String email, @RequestParam(name="adr") String adr) {
		Dealer builder = new Dealer();
		builder.setName(bldnme);
		builder.setPoc(poc);
		builder.setPhnnbr(phnnbr);
		builder.setEmail(email);
		builder.setAdr(adr);
		builder.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info(principal.getName()+" customer save :"+builder);
		if(dealerService.saveCustomer(builder))
		   return "true";
		else
		   return "false";
	}
	
	@RequestMapping(value = "/dealer/delete", method = RequestMethod.GET)
	public String productDelete(Principal principal,@RequestParam(name="bldId") Integer bldId) {
		logger.info(principal.getName()+" dealer delete :"+bldId);
		if(dealerService.delete(bldId,userService.findUserByEmail(principal.getName()).getUsrId()))
			return "true";
		else
			return "false";
	}
}
