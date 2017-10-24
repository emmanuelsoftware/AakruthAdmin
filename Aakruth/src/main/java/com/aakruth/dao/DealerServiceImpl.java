package com.aakruth.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.aakruth.model.Dealer;
import com.aakruth.model.DealerDisplay;
@Service("dealerService")
public class DealerServiceImpl implements DealerService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DealerRepository dealerRepository;
	@Override
	public Dealer findOne(Integer id) {
		
		return dealerRepository.findOne(id);
	}

	@Override
	public DataTablesOutput<Dealer> findAllCustomers(DataTablesInput input) {
		
		return dealerRepository.findAll(input,DealerSpecification.who('C'));
	}

	@Override
	public DataTablesOutput<Dealer> findAllBuilders(DataTablesInput input) {
		
		return dealerRepository.findAll(input,DealerSpecification.who('B'));
	}

	@Override
	public boolean saveCustomer(Dealer dealer) {
		try{
			dealer.setStrdte(new Date());
			dealer.setEnddte(new Date("01/01/9999"));
			dealer.setSta('L');
			dealer.setType('C');
			dealerRepository.save(dealer);
		}catch(Exception ex){
			logger.error("Error while  saving customer:"+dealer);
			return false;
		}
		return true;
	}
	@Override
	public boolean saveBuilder(Dealer dealer) {
		try{
			dealer.setStrdte(new Date());
			dealer.setEnddte(new Date("01/01/9999"));
			dealer.setSta('L');
			dealer.setType('B');
			dealerRepository.save(dealer);
		}catch(Exception ex){
			logger.error("Error while  saving builder:"+dealer);
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(Dealer dealer) {
		
		try{
			dealerRepository.save(dealer);
		}catch(Exception ex){
			logger.error("Error while  editng:"+dealer);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer id,Integer usrId) {
		try{
			Dealer dealer = dealerRepository.findOne(id);
			dealer.setUsrId(usrId);
			dealerRepository.save(dealer);
			dealerRepository.delete(id);
		}catch(Exception ex){
			logger.error("Error while  deleting:"+id);
			return false;
		}
		return true;
	}

	@Override
	public List<Dealer> findByStaAndType(char type) {
		return dealerRepository.findByStaAndType('L',type);
	}

	@Override
	public List<Dealer> findBySta() {
		return dealerRepository.findBySta('L');
	}

	@Override
	public List<DealerDisplay> findDealerDisplay() {
		
		return dealerRepository.findDealerDisplay();
	}

}
