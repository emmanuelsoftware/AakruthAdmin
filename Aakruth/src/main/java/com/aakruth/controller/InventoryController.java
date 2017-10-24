package com.aakruth.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakruth.dao.ProductService;
import com.aakruth.dao.PurchaseService;
import com.aakruth.dao.SaleService;
import com.aakruth.model.Dealer;
import com.aakruth.model.InventoryBean;
import com.aakruth.model.PrdTbl;

@RestController
public class InventoryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	SaleService saleService;

	@RequestMapping(value = "/inventory/onLoad")
	public List<InventoryBean> getInventories() {
		Date today = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		Date dateFromOneMonthBefore = Date
				.from(LocalDateTime.now().minusMonths(1).atZone(ZoneId.systemDefault()).toInstant());
		List<PrdTbl> products = productService.findAll();
		Map<Integer, Long> openingSale = saleService.findProductSale(new Date("01/01/1992"), dateFromOneMonthBefore);
		Map<Integer, Long> productsSold = saleService.findProductSale(dateFromOneMonthBefore, today);
		Map<Integer, Long> openingPurchase = purchaseService.findProductPurchase(new Date("01/01/1992"),
				dateFromOneMonthBefore);
		Map<Integer, Long> productsPurchased = purchaseService.findProductPurchase(dateFromOneMonthBefore, today);
		Map<Integer, Long> damagedProducts = saleService.findProductDamaged(dateFromOneMonthBefore, today, 0);
		List<InventoryBean> inventories = new ArrayList<>();
		for (PrdTbl product : products) {
			Dealer dealer = product.getDealer();
			Integer prdId = product.getPrdId();
			long damagedCnt = damagedProducts.getOrDefault(prdId, 0L);
			long saleCnt = productsSold.getOrDefault(prdId, 0L) - damagedCnt;
			long purchaseCnt = productsPurchased.getOrDefault(prdId, 0L);
			long stockOpen = openingPurchase.getOrDefault(prdId, 0L) - openingSale.getOrDefault(prdId, 0L);
			long stockClose = stockOpen + (purchaseCnt - saleCnt);
			InventoryBean inventory = new InventoryBean(product.getPrdId(), product.getPrdnme(),dealer.getName(), saleCnt, purchaseCnt,
					damagedCnt, stockOpen, stockClose);
			inventories.add(inventory);
		}
		return inventories;
	}

	@RequestMapping(value = "/inventory/search", method = RequestMethod.GET, produces = "application/json")
	public List<InventoryBean> getInventoryRange(@RequestParam(name = "dateRange") String dateRange) {
		String fromDt = dateRange.substring(0, 10);
		String toDt = dateRange.substring(13);
		List<PrdTbl> products = productService.findAll();
		Map<Integer, Long> openingSale = saleService.findProductSale(new Date("01/01/1992"), new Date(fromDt));
		Map<Integer, Long> productsSold = saleService.findProductSale( new Date(fromDt),  new Date(toDt));
		Map<Integer, Long> openingPurchase = purchaseService.findProductPurchase(new Date("01/01/1992"),
				 new Date(fromDt));
		Map<Integer, Long> productsPurchased = purchaseService.findProductPurchase(new Date(fromDt),  new Date(toDt));
		Map<Integer, Long> damagedProducts = saleService.findProductDamaged(new Date(fromDt),  new Date(toDt), 0);
		List<InventoryBean> inventories = new ArrayList<>();
		for (PrdTbl product : products) {
			Dealer dealer = product.getDealer();
			Integer prdId = product.getPrdId();
			long damagedCnt = damagedProducts.getOrDefault(prdId, 0L);
			long saleCnt = productsSold.getOrDefault(prdId, 0L) - damagedCnt;
			long purchaseCnt = productsPurchased.getOrDefault(prdId, 0L);
			long stockOpen = openingPurchase.getOrDefault(prdId, 0L) - openingSale.getOrDefault(prdId, 0L);
			long stockClose = stockOpen + (purchaseCnt - saleCnt);
			InventoryBean inventory = new InventoryBean(product.getPrdId(), product.getPrdnme(),dealer.getName(), saleCnt, purchaseCnt,
					damagedCnt, stockOpen, stockClose);
			inventories.add(inventory);
		}
		return inventories;
	}
}
