package com.aakruth.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.Transaction;

@Repository("transactionRepository")
public interface TransactionRepository extends DataTablesRepository<Transaction, Integer>,CrudRepository<Transaction, Integer>{
		List<Transaction> findByType(char type);
}
