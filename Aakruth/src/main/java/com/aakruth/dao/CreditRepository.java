package com.aakruth.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakruth.model.Credit;
import com.aakruth.model.Dealer;
import com.aakruth.model.Ledger;
import com.aakruth.model.Statement;

@Repository("creditRepository")
interface CreditRepository extends DataTablesRepository<Credit, Integer>,CrudRepository<Credit, Integer>{
	List<Credit> findBySta(char sta);
	List<Credit> findByStrdteBetweenOrderByStrdte(Date start, Date end);
	List<Credit> findByDealerAndStrdteBetweenOrderByStrdte(Dealer dealer,Date start, Date end);
	
	@Query(value = "SELECT sum(credit.amount) FROM com.aakruth.model.Credit credit WHERE credit.dealer.dealerId=:dealerId and credit.strdte between :start and :end ")
	BigDecimal findCreditsByDealerAndStrDteBetween(@Param("dealerId") Integer dealerId, @Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT sum(credit.amount) FROM com.aakruth.model.Credit credit WHERE credit.strdte between :start and :end ")
	BigDecimal findCreditsByStrDteBetween(@Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT new com.aakruth.model.Ledger(credit.strdte,credit.transaction.detail, credit.amount*0,credit.amount,credit.vat) FROM  com.aakruth.model.Credit credit WHERE credit.dealer.dealerId=:dealerId and credit.strdte between :start and :end ORDER BY credit.strdte ")
	List<Ledger> getLedgers(@Param("dealerId") Integer dealerId, @Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT new com.aakruth.model.Statement(credit.transaction.detail, SUM(credit.amount*100/credit.vat)) FROM  com.aakruth.model.Credit credit WHERE credit.dealer.dealerId=:dealerId and credit.strdte between :start and :end GROUP BY credit.transaction.detail  ORDER BY credit.strdte ")
	List<Statement> getStmtDealer(@Param("dealerId") Integer dealerId, @Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT new com.aakruth.model.Statement(credit.transaction.detail, SUM(credit.amount*100/credit.vat)) FROM  com.aakruth.model.Credit credit WHERE credit.strdte between :start and :end GROUP BY credit.transaction.detail ORDER BY credit.strdte ")
	List<Statement> getStmt( @Param("start") Date start, @Param("end")Date end);
}
