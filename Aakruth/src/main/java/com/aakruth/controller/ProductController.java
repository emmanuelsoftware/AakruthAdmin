package com.aakruth.controller;

import java.math.BigDecimal;
import java.security.Principal;
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

import com.aakruth.dao.DealerService;
import com.aakruth.dao.ProductService;
import com.aakruth.dao.PurchaseService;
import com.aakruth.dao.SaleService;
import com.aakruth.dao.UserService;
import com.aakruth.model.Dealer;
import com.aakruth.model.DealerDisplay;
import com.aakruth.model.PrdTbl;
import com.aakruth.model.PurchaseDisplay;
import com.aakruth.model.SalesDisplay;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductService productService;

	@Autowired
	private DealerService builderService;
	
	@Autowired
	UserService userService;

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/product/onLoad", method = RequestMethod.GET)
	public DataTablesOutput<PrdTbl> getProducts(@Valid DataTablesInput input) {
		return productService.findAll(input);
	}
	
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public List<PrdTbl> getProductList(@RequestParam(name="bldId") Integer bldId) {
		Dealer dealer = builderService.findOne(bldId);
		return productService.findByStaAndDealer('L',dealer);
	}
	
	@RequestMapping(value = "/product/edit", method = RequestMethod.GET)
	public String productEdit(Principal principal,@RequestParam(name="prdId") Integer prdId, @RequestParam(name="amc") Short amc, @RequestParam(name="amt") BigDecimal amt) {
		PrdTbl product = productService.findOne(prdId);
		logger.info(principal.getName()+" product edit before:"+product);
		product.setAmc(amc);
		product.setAmt(amt);
		product.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info(principal.getName()+" product edit after:"+product);
		productService.save(product);
		return "true";
	}
	
	@RequestMapping(value = "/product/save", method = RequestMethod.GET)
	public String productSave(Principal principal,@RequestParam(name="amc") Short amc, @RequestParam(name="amt") BigDecimal amt,@RequestParam(name="bldId") Integer bldId, @RequestParam(name="prdnme") String prdnme,@RequestParam(name="type") String type) {
		Dealer builder = builderService.findOne(bldId);
		PrdTbl product = new PrdTbl();
		product.setAmc(amc);
		product.setAmt(amt);
		product.setPrdnme(prdnme);
		product.setTyp(type);
		product.setDealer(builder);
		product.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info(principal.getName()+" product save:"+product);
		productService.save(product);
		return "true";
	}
	
	@RequestMapping(value = "/product/delete", method = RequestMethod.GET)
	public String productDelete(Principal principal,@RequestParam(name="prdId") Integer prdId) {
		logger.info(principal.getName()+" product delete:"+prdId);
		if(productService.delete(prdId,userService.findUserByEmail(principal.getName()).getUsrId()))
			return "true";
		else
			return "false";
	}

}
