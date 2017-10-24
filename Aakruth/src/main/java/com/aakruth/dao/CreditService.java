package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.repository.query.Param;

import com.aakruth.model.Credit;
import com.aakruth.model.Ledger;
import com.aakruth.model.Statement;
import com.aakruth.model.Transaction;


public interface CreditService {

	Credit findOne(Integer credId);
	DataTablesOutput<Credit> findAll(DataTablesInput input);
	boolean save(Credit credit);
	boolean edit(Credit credit);
	boolean delete(Integer credId,Integer usrId);
	List<Credit> findBySta(char sta);
	List<Transaction> findByType(char type);
	List<Credit> findByStrdteBetween(Date start, Date end);
	List<Credit> findByDealerAndStrdteBetween(Integer dealerId,Date start, Date end);
	BigDecimal findCreditsByDealerAndStrDteBetween(Integer dealerId,Date start,Date end);
	BigDecimal findCreditsByStrDteBetween(Date start,Date end);
	List<Ledger> getLedgers(Integer dealerId,Date start,Date end);
	List<Statement> getStmtDealer(Integer dealerId,Date start,Date end);
	List<Statement> getStmt(Date start,Date end);
}
