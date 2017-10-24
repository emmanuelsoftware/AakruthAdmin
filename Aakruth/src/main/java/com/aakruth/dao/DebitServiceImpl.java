package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.aakruth.model.Credit;
import com.aakruth.model.Dealer;
import com.aakruth.model.Debit;
import com.aakruth.model.Ledger;
import com.aakruth.model.Statement;
import com.aakruth.model.Transaction;

@Service("debitService")
public class DebitServiceImpl implements DebitService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DebitRepository debitRepository;
	
	@Autowired
	DealerRepository dealerRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public Debit findOne(Integer debId) {

		return debitRepository.findOne(debId);
	}

	@Override
	public DataTablesOutput<Debit> findAll(DataTablesInput input) {

		return debitRepository.findAll(input);
	}

	@Override
	public boolean save(Debit debit) {

		try {
			debitRepository.save(debit);

		} catch (Exception ex) {
			logger.error("Error while saving :"+debit);
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(Debit builder) {

		try {

		} catch (Exception ex) {
			logger.error("Error while  editing:"+builder);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer debId,Integer usrId) {

		try {
			Debit debit = debitRepository.findOne(debId);
			debit.setUsrId(usrId);
			debitRepository.save(debit);
			debitRepository.delete(debId);
		} catch (Exception ex) {
			logger.error("Error while  deleting:"+debId);
			return false;
		}
		return true;
	}

	@Override
	public List<Debit> findByStaAndTypeOfPayment(char type) {
		return debitRepository.findByStaAndTypeOfPayment('L',type);
	}

	@Override
	public List<Transaction> findByType(char type) {
		return transactionRepository.findByType('D');
	}

	@Override
	public List<Debit> findByStrdteBetween(Date start, Date end) {
		return debitRepository.findByStrdteBetweenOrderByStrdte(start, end);
	}

	@Override
	public List<Debit> findByDealerAndStrdteBetween(Integer dealerId, Date start, Date end) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		return debitRepository.findByDealerAndStrdteBetweenOrderByStrdte(dealer,start, end);
	}

	@Override
	public BigDecimal findDebitsByDealerAndStrDteBetween(Integer dealerId, Date start, Date end) {
		return debitRepository.findDebitsByDealerAndStrDteBetween(dealerId, start, end);
	}

	@Override
	public BigDecimal findDebitsByStrDteBetween(Date start, Date end) {
		// TODO Auto-generated method stub
		return debitRepository.findDebitsByStrDteBetween(start, end);
	}

	@Override
	public List<Ledger> getLedgers(Integer dealerId, Date start, Date end) {
		// TODO Auto-generated method stub
		return debitRepository.getLedgers(dealerId, start, end);
	}
	@Override
	public List<Statement> getStmtDealer(Integer dealerId, Date start, Date end) {
		// TODO Auto-generated method stub
		return debitRepository.getStmtDealer(dealerId, start, end);
	}
	
	@Override
	public List<Statement> getStmt(Date start, Date end) {
		// TODO Auto-generated method stub
		return debitRepository.getStmt( start, end);
	}

}
