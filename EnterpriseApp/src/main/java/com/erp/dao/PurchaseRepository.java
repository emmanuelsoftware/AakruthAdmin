package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.model.ProductAvail;
import com.erp.model.ProductChart;
import com.erp.model.Purchase;
import com.erp.model.PurchaseChart;
import com.erp.model.PurchaseDisplay;
@Repository("purchaseRepository")
interface PurchaseRepository extends DataTablesRepository<Purchase, Integer>,CrudRepository<Purchase, Integer>{

	List<Purchase> findBySta(char sta);
	//List<Purchase> findByPrdTbl(PrdTbl product);
	
	@Query(value = "SELECT sum(purchase.cnt) FROM com.erp.model.Purchase purchase WHERE purchase.product.prdId =:prdId ")
	Integer getPurchaseProductCount(@Param("prdId") Integer prdId);
	
	@Query(value = "SELECT new com.erp.model.PurchaseChart(purchase.purchaseBill.entryDte, sum(purchase.purAmt*purchase.cnt)) FROM com.erp.model.Purchase purchase WHERE purchase.purchaseBill.entryDte BETWEEN :start AND :end group by purchase.purchaseBill.entryDte")
	List<PurchaseChart> findPurchaseChart(@Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.erp.model.ProductChart(purchase.product.prdnme, sum(purchase.cnt)) FROM com.erp.model.Purchase purchase group by purchase.product.prdId")
	List<ProductChart> findProductChart();
	
	@Query(value = "SELECT new com.erp.model.PurchaseDisplay(purchase.purId, purchase.cnt) FROM com.erp.model.Purchase purchase")
	List<PurchaseDisplay> findPurchaseDisplay();
	
	@Query(value = "SELECT new com.erp.model.ProductAvail(purchase.product.prdId, sum(purchase.cnt)) FROM com.erp.model.Purchase purchase WHERE purchase.purchaseBill.entryDte BETWEEN :start AND :end group by purchase.product.prdId")
	List<ProductAvail> findProductPurchase(@Param("start") Date start, @Param("end") Date end);
}
