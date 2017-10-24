package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.model.SaleBill;

@Repository("saleBillRepository")
interface SaleBillRepository extends DataTablesRepository<SaleBill, Integer>,CrudRepository<SaleBill, Integer>{
	List<SaleBill> findByEntryDteBetween(Date from, Date to);
}
