package com.aakruth.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakruth.model.ProductAvail;
import com.aakruth.model.ProductChart;
import com.aakruth.model.PurTbl;
import com.aakruth.model.PurchaseChart;
import com.aakruth.model.PurchaseDisplay;
@Repository("purchaseRepository")
interface PurchaseRepository extends DataTablesRepository<PurTbl, Integer>,CrudRepository<PurTbl, Integer>{

	List<PurTbl> findBySta(char sta);
	//List<PurTbl> findByPrdTbl(PrdTbl product);
	
	@Query(value = "SELECT sum(purchase.cnt) FROM com.aakruth.model.PurTbl purchase WHERE purchase.prdTbl.prdId =:prdId ")
	Integer getPurchaseProductCount(@Param("prdId") Integer prdId);
	
	@Query(value = "SELECT new com.aakruth.model.PurchaseChart(purchase.purdte, sum(purchase.prdTbl.amt*purchase.cnt)) FROM com.aakruth.model.PurTbl purchase WHERE purchase.purdte BETWEEN :start AND :end group by purchase.purdte")
	List<PurchaseChart> findPurchaseChart(@Param("start") Date start, @Param("end") Date end);
	
	@Query(value = "SELECT new com.aakruth.model.ProductChart(purchase.prdTbl.prdnme, sum(purchase.cnt)) FROM com.aakruth.model.PurTbl purchase group by purchase.prdTbl.prdId")
	List<ProductChart> findProductChart();
	
	@Query(value = "SELECT new com.aakruth.model.PurchaseDisplay(purchase.purId, purchase.cnt) FROM com.aakruth.model.PurTbl purchase")
	List<PurchaseDisplay> findPurchaseDisplay();
	
	@Query(value = "SELECT new com.aakruth.model.ProductAvail(purchase.prdTbl.prdId, sum(purchase.cnt)) FROM com.aakruth.model.PurTbl purchase WHERE purchase.purdte BETWEEN :start AND :end group by purchase.prdTbl.prdId")
	List<ProductAvail> findProductPurchase(@Param("start") Date start, @Param("end") Date end);
}
