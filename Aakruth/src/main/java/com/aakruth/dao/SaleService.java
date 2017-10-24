package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.repository.query.Param;

import com.aakruth.model.ProductAvail;
import com.aakruth.model.ProductChart;
import com.aakruth.model.SalTbl;
import com.aakruth.model.SaleChart;
import com.aakruth.model.SalesDisplay;
import com.aakruth.model.UsrTbl;


public interface SaleService {

	SalTbl findOne(Integer salId);
	DataTablesOutput<SalTbl> findAll(DataTablesInput input);
	boolean save(SalTbl sale);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean edit(SalTbl sale);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean delete(Integer salId,Integer usrId);
	List<SalTbl> findBySta(char sta);
	List<SalTbl> findByBillTbl(Integer billId);
	Integer getSaleProductCount(@Param("prdId") Integer prdId);
	BigDecimal saleTotal(Date startDt, Date endDte);
	List<SaleChart> findSaleChart();
	List<SaleChart> findSaleChart(UsrTbl user);
	List<ProductChart> findProductChart();
	List<ProductChart> findProductChart(UsrTbl user);
	List<SalesDisplay> findSalesDisplay();
	List<SalesDisplay> findSalesDisplay(UsrTbl user);
	Map<Integer,Long> findProductSale(Date start,Date end);
	Map<Integer,Long> findProductDamaged(Date start, Date end,Integer dealerId);
}
