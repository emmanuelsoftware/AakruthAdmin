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
import com.aakruth.model.PrdTbl;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductRepository productRepository;

	@Override
	public PrdTbl findOne(Integer prdId) {
		return productRepository.findOne(prdId);
	}

	@Override
	public DataTablesOutput<PrdTbl> findAll(DataTablesInput input) {
		return productRepository.findAll(input);
	}

	@Override
	public PrdTbl save(PrdTbl product) {
		product.setStrdte(new Date());
		product.setEnddte(new Date("01/01/9999"));
		product.setSta('L');
		return productRepository.save(product);
	}

	@Override
	public boolean delete(int prdId,Integer usrId) {
		try {
			PrdTbl product = productRepository.findOne(prdId);
			product.setUsrId(usrId);
			productRepository.save(product);
			productRepository.delete(prdId);
		} catch (Exception ex) {
			logger.error("Error while  deleting:"+prdId);
			return false;
		}
		return true;
	}

	@Override
	public PrdTbl edit(PrdTbl product) {
		return productRepository.save(product);
	}

	@Override
	public List<PrdTbl> findByStaAndDealer(char sta, Dealer dealer) {
		return productRepository.findByStaAndDealer(sta, dealer);
	}

	@Override
	public List<PrdTbl> findAll() {
		return (List<PrdTbl>) productRepository.findAll();
	}

}


