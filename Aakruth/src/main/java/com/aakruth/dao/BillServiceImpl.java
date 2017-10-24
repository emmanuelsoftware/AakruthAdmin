package com.aakruth.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.aakruth.model.BillTbl;
import com.aakruth.model.UsrTbl;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepository billRepository;

	@Override
	public BillTbl findOne(Integer billId) {
		return billRepository.findOne(billId);
	}

	@Override
	public DataTablesOutput<BillTbl> findAll(DataTablesInput input) {
		return billRepository.findAll(input);
	}

	@Override
	public boolean save(BillTbl bill) {
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
	public boolean edit(BillTbl bill) {

		try {
			billRepository.save(bill);
		} catch (Exception ex) {
			return false;

		}
		return true;
	}

	@Override
	public boolean delete(Integer billId,UsrTbl user) {
		try {
			BillTbl bill = billRepository.findOne(billId);
			bill.setUsrTbl(user);
			billRepository.save(bill);
			billRepository.delete(bill);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public List<BillTbl> findByEntryDteBetween(Date from, Date to) {
		return billRepository.findByEntryDteBetween(from, to);
	}

}
