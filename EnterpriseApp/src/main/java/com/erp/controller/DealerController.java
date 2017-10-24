package com.erp.controller;

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

import com.erp.dao.DealerService;
import com.erp.dao.NotificationService;
import com.erp.dao.UserService;
import com.erp.model.Dealer;
import com.erp.model.Notification;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class DealerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DealerService dealerService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	UserService userService;

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/builder/findAll", method = RequestMethod.GET)
	public DataTablesOutput<Dealer> getBuilders(@Valid DataTablesInput input) {
		return dealerService.findAllBuilders(input);
	}

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/client/findAll", method = RequestMethod.GET)
	public DataTablesOutput<Dealer> getCustomers(@Valid DataTablesInput input) {
		logger.error("inside client controller:");
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

	@RequestMapping(value = "/dealer/search", method = RequestMethod.GET)
	public Dealer search(@RequestParam(name = "dealerId") Integer dealerId) {
		return dealerService.findOne(dealerId);
	}

	@RequestMapping(value = "/builder/save", method = RequestMethod.GET)
	public String buildersave(Principal principal, @RequestParam(name = "dealerId") Integer dealerId,
			@RequestParam(name = "bldnme") String bldnme, @RequestParam(name = "poc") String poc,
			@RequestParam(name = "phnnbr") String phnnbr, @RequestParam(name = "email") String email,
			@RequestParam(name = "adr") String adr, @RequestParam(name = "gstin") String gstin) {
		Dealer dealer = null;
		if (dealerId == 0)
			dealer = new Dealer();
		else {
			dealer = dealerService.findOne(dealerId);
			logger.info(principal.getName() + " Builder before:" + dealer);
		}
		dealer.setName(bldnme);
		dealer.setPoc(poc);
		dealer.setPhnnbr(phnnbr);
		dealer.setEmail(email);
		dealer.setAdr(adr);
		dealer.setGstin(gstin);
		Integer audUser = userService.findUserByEmail(principal.getName()).getUsrId();
		dealer.setAudUsrId(audUser);
		logger.info(principal.getName() + " builder  after:" + dealer);
		dealerService.saveBuilder(dealer);

		notificationService.save(new Notification("Changed Builder " + bldnme, "ADMIN", audUser));
		return "true";
	}

	@RequestMapping(value = "/client/save", method = RequestMethod.GET)
	public String clientsave(Principal principal, @RequestParam(name = "dealerId") Integer dealerId,
			@RequestParam(name = "cldnme") String cldnme, @RequestParam(name = "poc") String poc,
			@RequestParam(name = "phnnbr") String phnnbr, @RequestParam(name = "email") String email,
			@RequestParam(name = "adr") String adr, @RequestParam(name = "gstin") String gstin) {
		Dealer dealer = null;
		if (dealerId == 0)
			dealer = new Dealer();
		else {
			dealer = dealerService.findOne(dealerId);
			logger.info(principal.getName() + " Client before:" + dealer);
		}
		dealer.setName(cldnme);
		dealer.setPoc(poc);
		dealer.setPhnnbr(phnnbr);
		dealer.setEmail(email);
		dealer.setAdr(adr);
		dealer.setGstin(gstin);
		Integer audUser = userService.findUserByEmail(principal.getName()).getUsrId();
		dealer.setAudUsrId(audUser);
		logger.info(principal.getName() + " client  after:" + dealer);
		dealerService.saveCustomer(dealer);
		notificationService.save(new Notification("Changed Client" + cldnme, "ADMIN", audUser));
		return "true";
	}

	@RequestMapping(value = "/dealer/delete", method = RequestMethod.GET)
	public String productDelete(Principal principal, @RequestParam(name = "dealerId") Integer dealerId) {
		logger.info(principal.getName() + " dealer delete :" + dealerId);
		Integer audUser = userService.findUserByEmail(principal.getName()).getUsrId();
		String name = dealerService.findOne(dealerId).getName();
		if (dealerService.delete(dealerId, audUser)) {
			notificationService.save(new Notification("Deleted Dealer " + name, "ADMIN", audUser));
			return "true";
		} else
			return "false";
	}

	@RequestMapping(value = "/customer/search", method = RequestMethod.GET)
	public List<Dealer> getCustomerSearch(@RequestParam(name = "value") String value) {
		return dealerService.findByPhnnbrOrName(value.trim(), value.trim());
	}
}
