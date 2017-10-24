package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.aakruth.model.ProductChart;
import com.aakruth.model.PurTbl;
import com.aakruth.model.PurchaseChart;
import com.aakruth.model.PurchaseDisplay;
import com.aakruth.model.UsrTbl;



public interface PurchaseService {

	PurTbl findOne(Integer purId);
	DataTablesOutput<PurTbl> findAll(DataTablesInput input);
	boolean save(PurTbl purchase);
	boolean edit(PurTbl purchase);
	boolean delete(Integer purId,UsrTbl user);
	List<PurTbl> findBySta(char sta);
	Integer getPurchaseProductCount(Integer prdId);
	BigDecimal purchaseTotal(Date from,Date to);
	List<PurchaseChart> findPurchaseChart();
	List<ProductChart> findProductChart();
	List<PurchaseDisplay> findPurchaseDisplay();
	Map<Integer,Long> findProductPurchase(Date start, Date end);
}
