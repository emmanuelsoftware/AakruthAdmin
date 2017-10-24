package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakruth.model.BillTbl;
import com.aakruth.model.ProductAvail;
import com.aakruth.model.ProductChart;
import com.aakruth.model.SalTbl;
import com.aakruth.model.SaleChart;
import com.aakruth.model.SalesDisplay;

@Repository("saleRepository")
interface SaleRepository extends DataTablesRepository<SalTbl, Integer>,CrudRepository<SalTbl, Integer>{

	List<SalTbl> findBySta(char sta);
	List<SalTbl> findByBillTbl(BillTbl bill);
	@Query(value = "SELECT sum(sale.cnt) FROM com.aakruth.model.SalTbl sale WHERE sale.prdTbl.prdId =:prdId ")
	Integer getSaleProductCount(@Param("prdId") Integer prdId);
	
	@Query(value = "SELECT new com.aakruth.model.SaleChart(sale.billTbl.strdte, sum(sale.amt*sale.cnt)) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.strdte BETWEEN :start AND :end group by sale.billTbl.strdte")
	List<SaleChart> findSaleChart(@Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.aakruth.model.SaleChart(sale.billTbl.strdte, sum(sale.amt*sale.cnt)) FROM com.aakruth.model.SalTbl sale  group by sale.billTbl.strdte")
	List<SaleChart> findSaleChart();
	
	@Query(value = "SELECT new com.aakruth.model.SaleChart(sale.billTbl.strdte, sum(sale.amt*sale.cnt)) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.usrTbl.usrId =:usrId and sale.billTbl.strdte BETWEEN :start AND :end group by sale.billTbl.strdte")
	List<SaleChart> findSaleChartByUsrTbl(@Param("usrId") Integer usrId, @Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.aakruth.model.ProductChart(sale.prdTbl.prdnme, sum(sale.cnt)) FROM com.aakruth.model.SalTbl sale group by sale.prdTbl.prdId")
	List<ProductChart> findProductChart();
	
	@Query(value = "SELECT new com.aakruth.model.ProductChart(sale.prdTbl.prdnme, sum(sale.cnt)) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.usrTbl.usrId =:usrId group by sale.prdTbl.prdId")
	List<ProductChart> findProductChartByUsrTbl(@Param("usrId") Integer usrId);
	
	@Query(value = "SELECT new com.aakruth.model.SalesDisplay(sale.billTbl.billId, sum(sale.cnt)) FROM com.aakruth.model.SalTbl sale group by sale.billTbl.billId")
	List<SalesDisplay> findSalesDisplay();
	
	@Query(value = "SELECT new com.aakruth.model.SalesDisplay(sale.billTbl.billId, sum(sale.cnt)) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.usrTbl.usrId =:usrId group by sale.billTbl.billId")
	List<SalesDisplay> findSalesDisplayByUsrTbl(@Param("usrId") Integer usrId);
	
	@Query(value = "SELECT sum(sale.amt*sale.cnt) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.billId =:billId ")
	BigDecimal findTotal(@Param("billId") Integer billId);

	@Query(value = "SELECT new com.aakruth.model.ProductAvail(sale.prdTbl.prdId, sum(sale.cnt)) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.strdte BETWEEN :start AND :end group by sale.prdTbl.prdId")
	List<ProductAvail> findProductSale(@Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.aakruth.model.ProductAvail(sale.prdTbl.prdId, sum(sale.cnt)) FROM com.aakruth.model.SalTbl sale WHERE sale.billTbl.strdte BETWEEN :start AND :end and sale.billTbl.dealer.dealerId =:dealerId group by sale.prdTbl.prdId")
	List<ProductAvail> findProductDamaged(@Param("start") Date start, @Param("end") Date end,@Param("dealerId") Integer dealerId);
	
}
	