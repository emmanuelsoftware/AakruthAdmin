package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.aakruth.model.Credit;
import com.aakruth.model.Debit;
import com.aakruth.model.Ledger;
import com.aakruth.model.Statement;
import com.aakruth.model.Transaction;

public interface DebitService {
	Debit findOne(Integer debId);
	DataTablesOutput<Debit> findAll(DataTablesInput input);
	boolean save(Debit debit);
	boolean edit(Debit debit);
	boolean delete(Integer debId,Integer usrId);
	List<Debit> findByStaAndTypeOfPayment(char type);
	List<Transaction> findByType(char type);
	List<Debit> findByStrdteBetween(Date start, Date end);
	List<Debit> findByDealerAndStrdteBetween(Integer dealerId,Date start, Date end);
	BigDecimal findDebitsByDealerAndStrDteBetween(Integer dealerId,Date start,Date end);
	BigDecimal findDebitsByStrDteBetween(Date start,Date end);
	List<Ledger> getLedgers(Integer dealerId,Date start,Date end);
	List<Statement> getStmtDealer(Integer dealerId,Date start,Date end);
	List<Statement> getStmt(Date start,Date end);
}
