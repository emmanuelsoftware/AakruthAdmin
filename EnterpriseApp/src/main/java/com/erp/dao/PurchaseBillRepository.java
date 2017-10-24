package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.model.PurchaseBill;

@Repository("billRepository")
interface PurchaseBillRepository extends DataTablesRepository<PurchaseBill, Integer>,CrudRepository<PurchaseBill, Integer>{
	List<PurchaseBill> findByEntryDteBetween(Date from, Date to);
}
