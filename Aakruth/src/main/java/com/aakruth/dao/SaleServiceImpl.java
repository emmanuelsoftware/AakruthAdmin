package com.aakruth.dao;

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

import com.aakruth.model.BillTbl;
import com.aakruth.model.ProductAvail;
import com.aakruth.model.ProductChart;
import com.aakruth.model.SalTbl;
import com.aakruth.model.SaleChart;
import com.aakruth.model.SalesDisplay;
import com.aakruth.model.UsrTbl;

@Service("saleService")
public class SaleServiceImpl implements SaleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SaleRepository saleRepository;

	@Autowired
	BillRepository billRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public SalTbl findOne(Integer salId) {

		return saleRepository.findOne(salId);
	}

	@Override
	public DataTablesOutput<SalTbl> findAll(DataTablesInput input) {

		return saleRepository.findAll(input);
	}

	@Override
	public boolean save(SalTbl sale) {
		try {
			saleRepository.save(sale);
		} catch (Exception ex) {
			logger.error("Error while saving:"+sale);
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(SalTbl sale) {

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
			SalTbl sale = saleRepository.findOne(salId);
			sale.setUsrId(usrId);
			saleRepository.save(sale);
			
			saleRepository.delete(salId);
		} catch (Exception ex) {
			logger.error("Error while  delting:"+salId);
			return false;
		}
		return true;
	}

	@Override
	public List<SalTbl> findBySta(char sta) {

		return null;
	}

	@Override
	public List<SalTbl> findByBillTbl(Integer billId) {
		BillTbl bill = billRepository.findOne(billId);
		return saleRepository.findByBillTbl(bill);
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
		List<BillTbl> bills = billRepository.findByEntryDteBetween(startDt, endDte);
		BigDecimal amount = new BigDecimal(0);
		Set<SalTbl> sales = null;
		for (BillTbl bill : bills) {
			sales = bill.getSalTbls();
			for (SalTbl sale : sales) {
				amount.add(sale.getAmt());
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
	public List<SaleChart> findSaleChart(UsrTbl user) {
		Date date = new Date();
		Date beforeOneMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		return saleRepository.findSaleChartByUsrTbl(user.getUsrId(), date, beforeOneMonth);
	}

	@Override
	public List<ProductChart> findProductChart(UsrTbl user) {
		return saleRepository.findProductChartByUsrTbl(user.getUsrId());
	}

	@Override
	public List<SalesDisplay> findSalesDisplay(UsrTbl user) {
		return saleRepository.findSalesDisplayByUsrTbl(user.getUsrId());
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
