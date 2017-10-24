package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.erp.model.PurchaseBill;
import com.erp.model.User;

public interface PurchaseBillService {
	PurchaseBill findOne(Integer billId);
	DataTablesOutput<PurchaseBill> findAll(DataTablesInput input);
	boolean save(PurchaseBill bill);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean edit(PurchaseBill bill);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean delete(Integer billId,User user);
	List<PurchaseBill> findByEntryDteBetween(Date from, Date to);

}
