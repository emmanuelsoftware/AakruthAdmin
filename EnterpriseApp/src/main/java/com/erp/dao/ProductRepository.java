package com.erp.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.model.Dealer;
import com.erp.model.Product;
@Repository("productRepository")
public interface ProductRepository extends DataTablesRepository<Product, Integer>, CrudRepository<Product, Integer>{
	Product findOne(Integer prdId);
	List<Product> findByStaAndDealer(char sta, Dealer dealer);
	List<Product> findByStaAndPrdnmeIgnoreCaseContaining(char sta, String prdnme);
} 