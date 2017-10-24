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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakruth.dao.CreditService;
import com.aakruth.dao.DealerService;
import com.aakruth.dao.DebitService;
import com.aakruth.dao.TransactionRepository;
import com.aakruth.dao.UserService;
import com.aakruth.model.Credit;
import com.aakruth.model.Debit;
import com.aakruth.model.Transaction;
import com.aakruth.model.UsrTbl;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class CreditController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CreditService creditService;

	@Autowired
	DealerService dealerService;

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	UserService userService;
	
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/credit/onLoad", method = RequestMethod.POST)
	public DataTablesOutput<Credit> getBuilders(@Valid @RequestBody DataTablesInput input) {
		return creditService.findAll(input);
	}
	@RequestMapping(value = "/credit/searchDate", method = RequestMethod.GET, produces = "application/json")
	public List<Credit> creditDateSearch(@RequestParam(name = "dateRange") String dateRange, @RequestParam(name = "dealerId") Integer dealerId) {
		String fromDt = dateRange.substring(0,10);
		String toDt = dateRange.substring(13);
		if(dealerId>=0){
			logger.info("Dealer selected:"+dealerId);
			return creditService.findByDealerAndStrdteBetween(dealerId,new Date(fromDt), new Date(toDt));
		}else{
			logger.info("Dealer not selected");
			return creditService.findByStrdteBetween(new Date(fromDt), new Date(toDt));
		}
	}
	
	@RequestMapping(value = "/credit/listTransaction", method = RequestMethod.GET)
	public List<Transaction> gettransactions() {
		List<Transaction> trans = creditService.findByType('C');
		return trans;
	}
	
	@RequestMapping(value = "/credit/search", method = RequestMethod.GET)
	public Credit creditSearch(@RequestParam(name = "credId") Integer credId) {
		logger.info("User credit search for "+credId);
		return creditService.findOne(credId);
	}
	@RequestMapping(value = "/credit/save", method = RequestMethod.GET)
	public String creditSave(Principal principal,@RequestParam(name = "credId") Integer credId,
			@RequestParam(name = "voucherNo") String voucherNo, @RequestParam(name = "amount") BigDecimal amount,
			@RequestParam(name = "vat") BigDecimal vat, @RequestParam(name = "particular") String particular,
			@RequestParam(name = "remarks") String remarks, @RequestParam(name = "typeOfPayment") Integer typeOfPayment,
			@RequestParam(name = "transaction") Integer transaction,
			@RequestParam(name = "dealerId") Integer dealerId) {
		Credit credit;
		if (credId != null && credId > 0) {
			credit = creditService.findOne(credId);
			logger.info("User credit edit before:"+credit);
			
		} else {
			credit = new Credit();
			credit.setStrdte(new Date());
			credit.setEnddte(new Date());
			credit.setSta('L');
		}
		credit.setVoucherNo(voucherNo);
		credit.setAmount(amount);
		credit.setVat(vat);
		credit.setParticulars(particular);
		credit.setRemarks(remarks);
		credit.setDealer(dealerService.findOne(dealerId));
		credit.setTransaction(transactionRepository.findOne(transaction));
		credit.setTypeOfPayment(typeOfPayment);
		credit.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
	    logger.info(principal.getName()+" credit save after:"+credit);
		if (creditService.save(credit))
			return "true";
		else
			return "false";
	}
	
	@RequestMapping(value = "/credit/delete", method = RequestMethod.GET)
	public String creditDelete(Principal principal,@RequestParam(name = "credId") Integer credId) {
		logger.info(principal.getName()+" credit delete:"+credId);
		if (creditService.delete(credId,userService.findUserByEmail(principal.getName()).getUsrId()))
			return "true";
		else
			return "false";
	}
}
