package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.erp.model.PurchaseBill;
import com.erp.model.User;

@Service("billService")
public class PurchaseBillServiceImpl implements PurchaseBillService {

	@Autowired
	PurchaseBillRepository billRepository;

	@Override
	public PurchaseBill findOne(Integer billId) {
		return billRepository.findOne(billId);
	}

	@Override
	public DataTablesOutput<PurchaseBill> findAll(DataTablesInput input) {
		return billRepository.findAll(input);
	}

	@Override
	public boolean save(PurchaseBill bill) {
		bill.setStrdte(new Date());
		bill.setEnddte(new Date("01/01/9999"));
		bill.setSta('L');
		try {
			billRepository.save(bill);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(PurchaseBill bill) {

		try {
			billRepository.save(bill);
		} catch (Exception ex) {
			return false;

		}
		return true;
	}

	@Override
	public boolean delete(Integer billId,User user) {
		try {
			PurchaseBill bill = billRepository.findOne(billId);
			bill.setUser(user);
			billRepository.save(bill);
			billRepository.delete(bill);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public List<PurchaseBill> findByEntryDteBetween(Date from, Date to) {
		return billRepository.findByEntryDteBetween(from, to);
	}

}
