package com.erp.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.repository.query.Param;

import com.erp.model.ProductChart;
import com.erp.model.Sale;
import com.erp.model.SaleChart;
import com.erp.model.SalesDisplay;
import com.erp.model.User;


public interface SaleService {

	Sale findOne(Integer salId);
	DataTablesOutput<Sale> findAll(DataTablesInput input);
	boolean save(Sale sale);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean edit(Sale sale);
	//@PreAuthorize("hasRole('ADMIN')")
	boolean delete(Integer salId,Integer usrId);
	List<Sale> findBySta(char sta);
	List<Sale> findBySaleBill(Integer billId);
	Integer getSaleProductCount(@Param("prdId") Integer prdId);
	BigDecimal saleTotal(Date startDt, Date endDte);
	List<SaleChart> findSaleChart();
	List<SaleChart> findSaleChart(User user);
	List<ProductChart> findProductChart();
	List<ProductChart> findProductChart(User user);
	List<SalesDisplay> findSalesDisplay();
	List<SalesDisplay> findSalesDisplay(User user);
	Map<Integer,Long> findProductSale(Date start,Date end);
	Map<Integer,Long> findProductDamaged(Date start, Date end,Integer dealerId);
}
