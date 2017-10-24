package com.aakruth.controller;

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

import com.aakruth.dao.BillService;
import com.aakruth.dao.DealerService;
import com.aakruth.dao.UserService;
import com.aakruth.model.BillTbl;
import com.aakruth.model.Dealer;
import com.aakruth.model.UsrTbl;

@RestController
public class BillController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BillService billService;
	@Autowired
	UserService userService;
	@Autowired
	DealerService dealerService;

	@RequestMapping(value = "/bill/onLoad", method = RequestMethod.GET)
	public DataTablesOutput<BillTbl> getBills(@Valid DataTablesInput input) {
		return billService.findAll(input);
	}
	@RequestMapping(value = "/bill/onLoadSearch", method = RequestMethod.GET)
	public List<BillTbl> getBillSearch(@RequestParam(name = "dateRange") String dateRange) {
		String fromDt = dateRange.substring(0,10);
		String toDt = dateRange.substring(13);
		return billService.findByEntryDteBetween(new Date(fromDt), new Date(toDt));
	}
	
	@RequestMapping(value = "/bill/edit", method = RequestMethod.GET)
	public String billEdit(Principal principal,@RequestParam(name = "billId") Integer billId, @RequestParam(name = "poNum") String poNum,
			@RequestParam(name = "tax") BigDecimal tax) {
		BillTbl bill = billService.findOne(billId);
		logger.info(principal.getName()+" bill save before:"+bill);
		UsrTbl user = userService.findUserByEmail(principal.getName());
		
		logger.info("User Id at bill edit:"+user.getUsrId());
		bill.setUsrTbl(user);
		bill.setPoNum(poNum);
		bill.setTax(tax);
		logger.info("User bill save after:"+bill);
		if (billService.edit(bill))
			return "true";
		else
			return "false";
	}

	@RequestMapping(value = "/bill/save", method = RequestMethod.GET)
	public String billSave(Principal principal, @RequestParam(name = "dealerId") Integer dealerId,
			@RequestParam(name = "poNum") String poNum, @RequestParam(name = "tax") BigDecimal tax,
			@RequestParam(name = "entryDte") Date entryDte) {
		UsrTbl user = userService.findUserByEmail(principal.getName());
		Dealer dealer = dealerService.findOne(dealerId);
		BillTbl bill = new BillTbl();
		bill.setUsrTbl(user);
		bill.setDealer(dealer);
		bill.setEntryDte(entryDte);
		bill.setPoNum(poNum);
		bill.setTax(tax);
		logger.info(principal.getName()+" bill save:"+bill);
		logger.info("User Id at bill edit:"+user.getUsrId());
		if (billService.save(bill))
			return "true";
		else
			return "false";
	}

	@RequestMapping(value = "/bill/delete", method = RequestMethod.GET)
	public String billDelete(Principal principal,@RequestParam(name = "billId") Integer billId) {
		logger.info(principal.getName()+" bill delete action with data ID:"+billId);
		UsrTbl user = userService.findUserByEmail(principal.getName());
		logger.info("User Id at bill delete:"+user.getUsrId());
		if (billService.delete(billId,user))
			return "true";
		else
			return "false";
	}
}
