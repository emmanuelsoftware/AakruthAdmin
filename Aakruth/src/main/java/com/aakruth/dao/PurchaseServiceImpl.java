package com.aakruth.dao;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.aakruth.model.ProductAvail;
import com.aakruth.model.ProductChart;
import com.aakruth.model.PurTbl;
import com.aakruth.model.PurchaseChart;
import com.aakruth.model.PurchaseDisplay;
import com.aakruth.model.UsrTbl;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public PurTbl findOne(Integer purId) {

		return purchaseRepository.findOne(purId);
	}

	@Override
	public DataTablesOutput<PurTbl> findAll(DataTablesInput input) {

		return purchaseRepository.findAll(input);
	}

	@Override
	public boolean save(PurTbl purchase) {
		try {
			purchase.setEnddte(new Date("01/01/9999"));
			purchase.setSta('L');
			purchaseRepository.save(purchase);
		} catch (Exception ex) {
			logger.error("Error while  saving:"+purchase);
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(PurTbl purchase) {

		try {
			purchaseRepository.save(purchase);
		} catch (Exception ex) {
			logger.error("Error while  editing:"+purchase);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer purId,UsrTbl user) {
		try {
			PurTbl purchase = purchaseRepository.findOne(purId);
			purchase.setUsrTbl(user);
			purchaseRepository.save(purchase);
			purchaseRepository.delete(purId);
		} catch (Exception ex) {
			logger.error("Error while  deleting:"+purId);
			return false;
		}
		return true;
	}

	@Override
	public List<PurTbl> findBySta(char sta) {

		return purchaseRepository.findBySta(sta);
	}

	@Override
	public Integer getPurchaseProductCount(Integer prdId) {
		Integer count = purchaseRepository.getPurchaseProductCount(prdId);
		if (count != null)
			return count;
		else
			return 0;
	}

	@Override
	public BigDecimal purchaseTotal(Date from, Date to) {
		List<PurTbl> purchases = purchaseRepository.findBySta('L');
		BigDecimal amount = new BigDecimal(0);
		for (PurTbl purchase : purchases) {
			amount.add(purchase.getPrdTbl().getAmt().multiply(new BigDecimal(purchase.getCnt())));
		}
		return amount;
	}

	@Override
	public List<PurchaseChart> findPurchaseChart() {
		Date date = new Date();
		Date beforeOneMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		return purchaseRepository.findPurchaseChart(beforeOneMonth, date);
	}

	@Override
	public List<ProductChart> findProductChart() {
		return purchaseRepository.findProductChart();
	}

	@Override
	public List<PurchaseDisplay> findPurchaseDisplay() {

		return purchaseRepository.findPurchaseDisplay();
	}

	@Override
	public Map<Integer,Long> findProductPurchase(Date start, Date end) {
		return purchaseRepository.findProductPurchase(start, end).stream().collect(Collectors.toMap(ProductAvail::getPrdId, ProductAvail::getCount));
	}

}
