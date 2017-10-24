package com.erp.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.erp.model.Dealer;
import com.erp.model.Product;
import com.erp.model.Product_;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductRepository productRepository;

	Specification<Product> isActive() {
        return (root, query, cb) -> {
            return cb.equal(root.get(Product_.sta),'L');
        };
    }
	
	@Override
	public Product findOne(Integer prdId) {
		return productRepository.findOne(prdId);
	}

	@Override
	public DataTablesOutput<Product> findAll(DataTablesInput input) {
		return productRepository.findAll(input,isActive());
	}

	@Override
	public Product save(Product product) {
		product.setStrdte(new Date());
		product.setEnddte(new Date("01/01/9999"));
		product.setSta('L');
		return productRepository.save(product);
	}

	@Override
	public boolean delete(int prdId,Integer usrId) {
		try {
			Product product = productRepository.findOne(prdId);
			product.setAudUsrId(usrId);
			product.setSta('T');
			product.setEnddte(new Date());
			productRepository.save(product);
		} catch (Exception ex) {
			logger.error("Error while  deleting:"+prdId);
			return false;
		}
		return true;
	}

	@Override
	public List<Product> findByStaAndDealer(char sta, Dealer dealer) {
		return productRepository.findByStaAndDealer(sta, dealer);
	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> findByPrdnme(String prdnme) {
		return productRepository.findByStaAndPrdnmeIgnoreCaseContaining('L', prdnme);
	}

}


