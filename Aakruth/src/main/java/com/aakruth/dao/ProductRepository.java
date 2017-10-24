package com.aakruth.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.Dealer;
import com.aakruth.model.PrdTbl;
@Repository("productRepository")
public interface ProductRepository extends DataTablesRepository<PrdTbl, Integer>, CrudRepository<PrdTbl, Integer>{
	PrdTbl findOne(Integer prdId);
	List<PrdTbl> findByStaAndDealer(char sta, Dealer dealer);
}