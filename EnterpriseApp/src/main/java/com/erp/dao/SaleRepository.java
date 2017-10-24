package com.erp.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.model.SaleBill;
import com.erp.model.ProductAvail;
import com.erp.model.ProductChart;
import com.erp.model.Sale;
import com.erp.model.SaleChart;
import com.erp.model.SalesDisplay;

@Repository("saleRepository")
interface SaleRepository extends DataTablesRepository<Sale, Integer>,CrudRepository<Sale, Integer>{

	List<Sale> findBySta(char sta);
	List<Sale> findBySaleBill(SaleBill saleBill);
	@Query(value = "SELECT sum(sale.cnt) FROM com.erp.model.Sale sale WHERE sale.product.prdId =:prdId ")
	Integer getSaleProductCount(@Param("prdId") Integer prdId);
	
	@Query(value = "SELECT new com.erp.model.SaleChart(sale.saleBill.strdte, sum(sale.saleAmt*sale.cnt)) FROM com.erp.model.Sale sale WHERE sale.saleBill.strdte BETWEEN :start AND :end group by sale.saleBill.strdte")
	List<SaleChart> findSaleChart(@Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.erp.model.SaleChart(sale.saleBill.strdte, sum(sale.saleAmt*sale.cnt)) FROM com.erp.model.Sale sale  group by sale.saleBill.strdte")
	List<SaleChart> findSaleChart();
	
	@Query(value = "SELECT new com.erp.model.SaleChart(sale.saleBill.strdte, sum(sale.saleAmt*sale.cnt)) FROM com.erp.model.Sale sale WHERE sale.saleBill.user.usrId =:usrId and sale.saleBill.strdte BETWEEN :start AND :end group by sale.saleBill.strdte")
	List<SaleChart> findSaleChartByUser(@Param("usrId") Integer usrId, @Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.erp.model.ProductChart(sale.product.prdnme, sum(sale.cnt)) FROM com.erp.model.Sale sale group by sale.product.prdId")
	List<ProductChart> findProductChart();
	
	@Query(value = "SELECT new com.erp.model.ProductChart(sale.product.prdnme, sum(sale.cnt)) FROM com.erp.model.Sale sale WHERE sale.saleBill.user.usrId =:usrId group by sale.product.prdId")
	List<ProductChart> findProductChartByUser(@Param("usrId") Integer usrId);
	
	@Query(value = "SELECT new com.erp.model.SalesDisplay(sale.saleBill.billId, sum(sale.cnt)) FROM com.erp.model.Sale sale group by sale.saleBill.billId")
	List<SalesDisplay> findSalesDisplay();
	
	@Query(value = "SELECT new com.erp.model.SalesDisplay(sale.saleBill.billId, sum(sale.cnt)) FROM com.erp.model.Sale sale WHERE sale.saleBill.user.usrId =:usrId group by sale.saleBill.billId")
	List<SalesDisplay> findSalesDisplayByUser(@Param("usrId") Integer usrId);
	
	@Query(value = "SELECT sum(sale.saleAmt*sale.cnt) FROM com.erp.model.Sale sale WHERE sale.saleBill.billId =:billId ")
	BigDecimal findTotal(@Param("billId") Integer billId);

	@Query(value = "SELECT new com.erp.model.ProductAvail(sale.product.prdId, sum(sale.cnt)) FROM com.erp.model.Sale sale WHERE sale.saleBill.strdte BETWEEN :start AND :end group by sale.product.prdId")
	List<ProductAvail> findProductSale(@Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.erp.model.ProductAvail(sale.product.prdId, sum(sale.cnt)) FROM com.erp.model.Sale sale WHERE sale.saleBill.strdte BETWEEN :start AND :end and sale.saleBill.dealer.dealerId =:dealerId group by sale.product.prdId")
	List<ProductAvail> findProductDamaged(@Param("start") Date start, @Param("end") Date end,@Param("dealerId") Integer dealerId);
	
}
	