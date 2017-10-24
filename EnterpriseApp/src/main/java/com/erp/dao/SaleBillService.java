package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.erp.model.SaleBill;
import com.erp.model.User;

public interface SaleBillService {
	SaleBill findOne(Integer billId);
	DataTablesOutput<SaleBill> findAll(DataTablesInput input);
	boolean save(SaleBill bill);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean edit(SaleBill bill);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean delete(Integer billId,User user);
	List<SaleBill> findByEntryDteBetween(Date from, Date to);

}
