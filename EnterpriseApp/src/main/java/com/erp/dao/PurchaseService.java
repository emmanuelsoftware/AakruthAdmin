package com.erp.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.erp.model.ProductChart;
import com.erp.model.Purchase;
import com.erp.model.PurchaseChart;
import com.erp.model.PurchaseDisplay;
import com.erp.model.User;



public interface PurchaseService {

	Purchase findOne(Integer purId);
	DataTablesOutput<Purchase> findAll(DataTablesInput input);
	boolean save(Purchase purchase);
	boolean edit(Purchase purchase);
	boolean delete(Integer purId,User user);
	List<Purchase> findBySta(char sta);
	Integer getPurchaseProductCount(Integer prdId);
	BigDecimal purchaseTotal(Date from,Date to);
	List<PurchaseChart> findPurchaseChart();
	List<ProductChart> findProductChart();
	List<PurchaseDisplay> findPurchaseDisplay();
	Map<Integer,Long> findProductPurchase(Date start, Date end);
}
