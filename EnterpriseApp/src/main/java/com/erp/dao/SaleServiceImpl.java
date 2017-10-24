package com.erp.dao;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.erp.model.ProductAvail;
import com.erp.model.ProductChart;
import com.erp.model.Sale;
import com.erp.model.SaleBill;
import com.erp.model.SaleChart;
import com.erp.model.SalesDisplay;
import com.erp.model.User;

@Service("saleService")
public class SaleServiceImpl implements SaleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SaleRepository saleRepository;

	@Autowired
	SaleBillRepository billRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Sale findOne(Integer salId) {

		return saleRepository.findOne(salId);
	}

	@Override
	public DataTablesOutput<Sale> findAll(DataTablesInput input) {

		return saleRepository.findAll(input);
	}

	@Override
	public boolean save(Sale sale) {
		try {
			saleRepository.save(sale);
		} catch (Exception ex) {
			logger.error("Error while saving:"+sale);
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(Sale sale) {

		try {
			saleRepository.save(sale);
		} catch (Exception ex) {
			logger.error("Error while  editing:"+sale);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer salId,Integer usrId) {

		try {
			Sale sale = saleRepository.findOne(salId);
			sale.setAudUsrId(usrId);
			saleRepository.save(sale);
			
			saleRepository.delete(salId);
		} catch (Exception ex) {
			logger.error("Error while  delting:"+salId);
			return false;
		}
		return true;
	}

	@Override
	public List<Sale> findBySta(char sta) {

		return null;
	}

	@Override
	public List<Sale> findBySaleBill(Integer billId) {
		SaleBill bill = billRepository.findOne(billId);
		return saleRepository.findBySaleBill(bill);
	}

	@Override
	public Integer getSaleProductCount(Integer prdId) {
		Integer count = saleRepository.getSaleProductCount(prdId);
		if (count != null)
			return count;
		else
			return 0;
	}

	@Override
	public BigDecimal saleTotal(Date startDt, Date endDte) {
		List<SaleBill> bills = billRepository.findByEntryDteBetween(startDt, endDte);
		BigDecimal amount = new BigDecimal(0);
		Set<Sale> sales = null;
		for (SaleBill bill : bills) {
			sales = bill.getSales();
			for (Sale sale : sales) {
				amount.add(sale.getSaleAmt());
			}
		}
		return amount;
	}

	@Override
	public List<SaleChart> findSaleChart() {
		Date date = new Date();
		Date beforeOneMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		return saleRepository.findSaleChart(beforeOneMonth, date);
	}

	@Override
	public List<ProductChart> findProductChart() {
		return saleRepository.findProductChart();
	}

	@Override
	public List<SalesDisplay> findSalesDisplay() {

		return saleRepository.findSalesDisplay();
	}

	@Override
	public List<SaleChart> findSaleChart(User user) {
		Date date = new Date();
		Date beforeOneMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		return saleRepository.findSaleChartByUser(user.getUsrId(), date, beforeOneMonth);
	}

	@Override
	public List<ProductChart> findProductChart(User user) {
		return saleRepository.findProductChartByUser(user.getUsrId());
	}

	@Override
	public List<SalesDisplay> findSalesDisplay(User user) {
		return saleRepository.findSalesDisplayByUser(user.getUsrId());
	}

	@Override
	public Map<Integer,Long> findProductSale(Date start, Date end) {	
		return saleRepository.findProductSale(start, end).stream().collect(Collectors.toMap(ProductAvail::getPrdId, ProductAvail::getCount));
	}

	@Override
	public Map<Integer,Long> findProductDamaged(Date start, Date end, Integer dealerId) {
		return saleRepository.findProductDamaged(start, end, dealerId).stream().collect(Collectors.toMap(ProductAvail::getPrdId, ProductAvail::getCount));
	}
}
