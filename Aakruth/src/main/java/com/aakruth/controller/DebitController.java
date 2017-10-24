package com.aakruth.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
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

import com.aakruth.dao.DealerService;
import com.aakruth.dao.DebitService;
import com.aakruth.dao.TransactionRepository;
import com.aakruth.dao.UserService;
import com.aakruth.model.Debit;
import com.aakruth.model.Transaction;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class DebitController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DebitService debitService;

	@Autowired
	DealerService dealerService;

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired 
	UserService userService;

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/debit/onLoad", method = RequestMethod.POST)
	public DataTablesOutput<Debit> getDebits(@Valid @RequestBody DataTablesInput input) {
		return debitService.findAll(input);
	}

	@RequestMapping(value = "/debit/searchDate", method = RequestMethod.GET, produces = "application/json")
	public List<Debit> debitDateSearch(@RequestParam(name = "dateRange") String dateRange, @RequestParam(name = "dealerId") Integer dealerId) {
		String fromDt = dateRange.substring(0,10);
		String toDt = dateRange.substring(13);
		if(dealerId>=0){
			logger.info("Dealer selected:"+dealerId);
			return debitService.findByDealerAndStrdteBetween(dealerId,new Date(fromDt), new Date(toDt));
		}else{
			logger.info("Dealer not selected");
			return debitService.findByStrdteBetween(new Date(fromDt), new Date(toDt));
		}
	}
	@RequestMapping(value = "/debit/search", method = RequestMethod.GET, produces = "application/json")
	public Debit debitSearch(@RequestParam(name = "debId") Integer debId) {
		return debitService.findOne(debId);
	}

	@RequestMapping(value = "/debit/listTransaction", method = RequestMethod.GET, produces = "application/json")
	public List<Transaction> gettransactions() {
		List<Transaction> trans = debitService.findByType('D');
		return trans;
	}

	@RequestMapping(value = "/debit/save", method = RequestMethod.GET)
	public String debitSave(Principal principal,@RequestParam(name = "debId") Integer debId,
			@RequestParam(name = "voucherNo") String voucherNo, @RequestParam(name = "amount") BigDecimal amount,
			@RequestParam(name = "vat") BigDecimal vat, @RequestParam(name = "particular") String particular,
			@RequestParam(name = "remarks") String remarks, @RequestParam(name = "typeOfPayment") Integer typeOfPayment,
			@RequestParam(name = "transaction") Integer transaction,
			@RequestParam(name = "dealerId") Integer dealerId) {
		Debit debit;
		if (debId != null && debId > 0) {
			debit = debitService.findOne(debId);
			logger.info(principal.getName()+" debit before edit :" + debit);
		} else {
			debit = new Debit();
			debit.setStrdte(new Date());
			debit.setEnddte(new Date());
			debit.setSta('L');
		}
		debit.setVoucherNo(voucherNo);
		debit.setAmount(amount);
		debit.setVat(vat);
		debit.setParticular(particular);
		debit.setRemarks(remarks);
		debit.setDealer(dealerService.findOne(dealerId));
		debit.setTransaction(transactionRepository.findOne(transaction));
		debit.setTypeOfPayment(typeOfPayment);
		debit.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info(principal.getName()+" debit save :" + debit);
		if (debitService.save(debit))
			return "true";
		else
			return "false";
	}

	@RequestMapping(value = "/debit/delete", method = RequestMethod.GET)
	public String debitDelete(Principal principal,@RequestParam(name = "debId") Integer debId) {
		logger.info(principal.getName()+" debit delete :"+debId);
		if (debitService.delete(debId,userService.findUserByEmail(principal.getName()).getUsrId()))
			return "true";
		else
			return "false";
	}

}
