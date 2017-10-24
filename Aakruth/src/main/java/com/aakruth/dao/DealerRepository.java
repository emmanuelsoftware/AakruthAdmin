package com.aakruth.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.Dealer;
import com.aakruth.model.DealerDisplay;

@Repository("dealerRepository")
public interface DealerRepository extends DataTablesRepository<Dealer, Integer>, CrudRepository<Dealer, Integer> {
	List<Dealer> findByStaAndType(char sta, char type);

	List<Dealer> findBySta(char sta);
	
	@Query(value = "SELECT new com.aakruth.model.DealerDisplay(dealer.dealerId, dealer.type) FROM com.aakruth.model.Dealer dealer")
	List<DealerDisplay> findDealerDisplay();
}
