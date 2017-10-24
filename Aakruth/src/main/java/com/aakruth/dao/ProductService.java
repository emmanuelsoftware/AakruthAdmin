package com.aakruth.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;

import com.aakruth.model.Dealer;
import com.aakruth.model.PrdTbl;

public interface ProductService {

	PrdTbl findOne(Integer prdId);
	DataTablesOutput<PrdTbl> findAll(DataTablesInput input);
	PrdTbl save(PrdTbl product);
	PrdTbl edit(PrdTbl product);
	boolean delete(int prdId,Integer usrId);
	List<PrdTbl> findByStaAndDealer(char sta, Dealer dealer);
    List<PrdTbl> findAll();
	
}
