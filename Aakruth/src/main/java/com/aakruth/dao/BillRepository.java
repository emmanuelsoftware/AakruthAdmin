package com.aakruth.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.BillTbl;

@Repository("billRepository")
interface BillRepository extends DataTablesRepository<BillTbl, Integer>,CrudRepository<BillTbl, Integer>{
	List<BillTbl> findByEntryDteBetween(Date from, Date to);
}
