package com.aakruth.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.aakruth.model.Dealer;
import com.aakruth.model.DealerDisplay;

public interface DealerService {

	Dealer findOne(Integer id);
	DataTablesOutput<Dealer> findAllCustomers(DataTablesInput input);
	DataTablesOutput<Dealer> findAllBuilders(DataTablesInput input);
	boolean saveCustomer(Dealer dealer);
	boolean saveBuilder(Dealer dealer);
	boolean edit(Dealer dealer);
	boolean delete(Integer id,Integer usrId);
	List<Dealer> findByStaAndType(char type);
	List<Dealer> findBySta();
	List<DealerDisplay> findDealerDisplay();
}
