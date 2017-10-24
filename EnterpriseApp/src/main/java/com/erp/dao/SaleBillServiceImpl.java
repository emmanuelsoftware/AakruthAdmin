package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.erp.model.Product;
import com.erp.model.Product_;
import com.erp.model.SaleBill;
import com.erp.model.SaleBill_;
import com.erp.model.User;

@Service("saleBillService")
public class SaleBillServiceImpl implements SaleBillService {

	@Autowired
	SaleBillRepository saleBillRepository;

	Specification<SaleBill> isActive() {
        return (root, query, cb) -> {
            return cb.equal(root.get(SaleBill_.sta),'L');
        };
    }
	
	@Override
	public SaleBill findOne(Integer billId) {
		return saleBillRepository.findOne(billId);
	}

	@Override
	public DataTablesOutput<SaleBill> findAll(DataTablesInput input) {
		return saleBillRepository.findAll(input,isActive());
	}

	@Override
	public boolean save(SaleBill bill) {
		bill.setStrdte(new Date());
		bill.setEnddte(new Date("01/01/9999"));
		bill.setSta('L');
		try {
			saleBillRepository.save(bill);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(SaleBill bill) {

		try {
			saleBillRepository.save(bill);
		} catch (Exception ex) {
			return false;

		}
		return true;
	}

	@Override
	public boolean delete(Integer billId,User user) {
		try {
			SaleBill bill = saleBillRepository.findOne(billId);
			bill.setUser(user);
			bill.setSta('T');
			saleBillRepository.save(bill);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public List<SaleBill> findByEntryDteBetween(Date from, Date to) {
		return saleBillRepository.findByEntryDteBetween(from, to);
	}

}
