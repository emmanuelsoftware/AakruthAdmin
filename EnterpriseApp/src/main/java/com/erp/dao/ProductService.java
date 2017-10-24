package com.erp.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.erp.model.Dealer;
import com.erp.model.Product;

public interface ProductService {

	Product findOne(Integer prdId);
	DataTablesOutput<Product> findAll(DataTablesInput input);
	Product save(Product product);
	boolean delete(int prdId,Integer usrId);
	List<Product> findByStaAndDealer(char sta, Dealer dealer);
    List<Product> findAll();
    List<Product> findByPrdnme(String prdnme);
	
}
