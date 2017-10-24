package com.aakruth.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakruth.model.Dealer;
import com.aakruth.model.Debit;
import com.aakruth.model.Ledger;
import com.aakruth.model.Statement;
@Repository("debitRepository")
interface DebitRepository extends DataTablesRepository<Debit, Integer>,CrudRepository<Debit, Integer>{
	List<Debit> findByStaAndTypeOfPayment(char sta,char type);
	List<Debit> findByStrdteBetweenOrderByStrdte(Date start, Date end);
	List<Debit> findByDealerAndStrdteBetweenOrderByStrdte(Dealer dealer,Date start, Date end);
	
	@Query(value = "SELECT sum(debit.amount) FROM com.aakruth.model.Debit debit WHERE debit.dealer.dealerId=:dealerId and debit.strdte between :start and :end ")
	BigDecimal findDebitsByDealerAndStrDteBetween(@Param("dealerId") Integer dealerId, @Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT sum(debit.amount) FROM com.aakruth.model.Debit debit WHERE debit.strdte between :start and :end ")
	BigDecimal findDebitsByStrDteBetween(@Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT new com.aakruth.model.Ledger(debit.strdte,debit.transaction.detail, debit.amount,debit.amount*0,debit.vat) FROM  com.aakruth.model.Debit debit WHERE debit.dealer.dealerId=:dealerId and debit.strdte between :start and :end ORDER BY debit.strdte ")
	List<Ledger> getLedgers(@Param("dealerId") Integer dealerId, @Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT new com.aakruth.model.Statement(debit.transaction.detail, SUM(debit.amount*100/debit.vat)) FROM  com.aakruth.model.Debit debit WHERE debit.dealer.dealerId=:dealerId and debit.strdte between :start and :end GROUP BY debit.transaction.detail  ORDER BY debit.transaction.detail ")
	List<Statement> getStmtDealer(@Param("dealerId") Integer dealerId, @Param("start") Date start, @Param("end")Date end);
	
	@Query(value = "SELECT new com.aakruth.model.Statement(debit.transaction.detail, SUM(debit.amount*100/debit.vat)) FROM  com.aakruth.model.Debit debit WHERE debit.strdte between :start and :end GROUP BY debit.transaction.detail ORDER BY debit.transaction.detail ")
	List<Statement> getStmt( @Param("start") Date start, @Param("end")Date end);
}
