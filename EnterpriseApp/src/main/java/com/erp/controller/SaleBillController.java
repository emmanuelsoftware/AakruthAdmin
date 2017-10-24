package com.erp.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
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
import com.erp.dao.SaleBillService;
import com.erp.dao.UserService;
import com.erp.model.Dealer;
import com.erp.model.SaleBill;
import com.erp.model.User;

@RestController
public class SaleBillController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SaleBillService billService;
	@Autowired
	UserService userService;
	@Autowired
	DealerService dealerService;

	@RequestMapping(value = "saleBill/findAll", method = RequestMethod.GET)
	public DataTablesOutput<SaleBill> getBills(@Valid DataTablesInput input) {
		return billService.findAll(input);
	}

	@RequestMapping(value = "/bill/onLoadSearch", method = RequestMethod.GET)
	public List<SaleBill> getBillSearch(@RequestParam(name = "dateRange") String dateRange) {
		String fromDt = dateRange.substring(0, 10);
		String toDt = dateRange.substring(13);
		return billService.findByEntryDteBetween(new Date(fromDt), new Date(toDt));
	}

	@RequestMapping(value = "/bill/findOne", method = RequestMethod.GET)
	public SaleBill getBill(@RequestParam(name = "billId") Integer saleBillId) {
		return billService.findOne(saleBillId);
	}

	@RequestMapping(value = "/bill/save", method = RequestMethod.GET)
	public String billEdit(Principal principal, @RequestParam(name = "billId") Integer billId,
			@RequestParam(name = "dealerId") Integer dealerId, @RequestParam(name = "entryDte") Date entryDte,
			@RequestParam(name = "poNum") String poNum, @RequestParam(name = "taxType") char taxType) {
		SaleBill bill = null;
		if (billId == null) {
			bill = new SaleBill();

		} else {
			bill = billService.findOne(billId);
			logger.info(principal.getName() + " bill save before:" + bill);
		}

		User user = userService.findUserByEmail(principal.getName());
		logger.info("User Id at bill save:" + user.getUsrId());
		bill.setUser(user);
		bill.setPoNum(poNum);
		bill.setDealer(dealerService.findOne(dealerId));
		bill.setTaxType(taxType);
		bill.setEntryDte(entryDte);
		logger.info("User bill save after:" + bill);
		if (billService.save(bill))
			return "true";
		else
			return "false";
	}

	@RequestMapping(value = "/bill/delete", method = RequestMethod.GET)
	public String billDelete(Principal principal, @RequestParam(name = "billId") Integer billId) {
		logger.info(principal.getName() + " bill delete action with data ID:" + billId);
		User user = userService.findUserByEmail(principal.getName());
		logger.info("User Id at bill delete:" + user.getUsrId());
		if (billService.delete(billId, user))
			return "true";
		else
			return "false";
	}
}
