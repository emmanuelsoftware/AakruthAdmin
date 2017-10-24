package com.aakruth.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakruth.dao.ProductService;
import com.aakruth.dao.PurchaseService;
import com.aakruth.dao.UserService;
import com.aakruth.model.PrdTbl;
import com.aakruth.model.ProductChart;
import com.aakruth.model.PurTbl;
import com.aakruth.model.PurchaseChart;
import com.aakruth.model.PurchaseDisplay;
import com.aakruth.model.UsrTbl;

@RestController
public class PurchaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/purchase/onLoad", method = RequestMethod.GET)
	public DataTablesOutput<PurTbl> getPurchases(@Valid DataTablesInput input) {
		return purchaseService.findAll(input);
	}
	
	@RequestMapping(value = "/purchase/edit", method = RequestMethod.GET)
	public String purchaseEdit(Principal principal,@RequestParam(name="purId") Integer purId,@RequestParam(name="invoice") Integer invoice,@RequestParam(name="cnt") Integer cnt, @RequestParam(name="vat") BigDecimal vat) {
		PurTbl purchase = purchaseService.findOne(purId);
		logger.info(principal.getName()+" purchase edit before:"+purchase);
		purchase.setInvoice(invoice);
		purchase.setCnt(cnt);
		purchase.setVat(vat);
		purchase.setUsrTbl(userService.findUserByEmail(principal.getName()));
		logger.info(principal.getName()+" purchase edit after:"+purchase);
		if(purchaseService.edit(purchase))
		   return "true";
		else
		   return "false";
	}
	
	@RequestMapping(value = "/purchase/save", method = RequestMethod.GET)
	public String purchaseSave(Principal principal,@RequestParam(name="prdId") Integer prdId,@RequestParam(name="invoice") Integer invoice,@RequestParam(name="cnt") Integer cnt, @RequestParam(name="tax") BigDecimal vat,@RequestParam(name="purdte") Date purdte) {
		UsrTbl user = userService.findUserByEmail(principal.getName());
		PrdTbl product = productService.findOne(prdId);
		PurTbl purchase = new PurTbl();
		purchase.setUsrTbl(user);
		purchase.setPrdTbl(product);
		purchase.setInvoice(invoice);
		purchase.setCnt(cnt);
		purchase.setVat(vat);
		purchase.setPurdte(purdte);
		logger.info(principal.getName()+" purchase save:"+purchase);
		if(purchaseService.save(purchase))
		   return "true";
		else
		   return "false";
	}
	
	@RequestMapping(value = "/purchase/delete", method = RequestMethod.GET)
	public String purchaseDelete(Principal principal,@RequestParam(name="purId") Integer purId) {
		logger.info("User purchase delete:"+purId);
		if(purchaseService.delete(purId,userService.findUserByEmail(principal.getName())))
			return "true";
		else
			return "false";
	}
	@RequestMapping(value = "/purchase/purchaseChart", method = RequestMethod.GET)
	public List<PurchaseChart> getPurchaseChart() {
		return purchaseService.findPurchaseChart();
	}
	
	@RequestMapping(value = "/purchase/productChart", method = RequestMethod.GET)
	public List<ProductChart> getProductChart() {
		return purchaseService.findProductChart();
	}
	
	@RequestMapping(value = "/purchase/purchaseDisplay", method = RequestMethod.GET)
	public List<PurchaseDisplay> getPurchaseDisplay() {
		return purchaseService.findPurchaseDisplay();
	}
}
