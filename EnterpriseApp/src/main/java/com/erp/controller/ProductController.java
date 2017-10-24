package com.erp.controller;

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

import com.erp.dao.DealerService;
import com.erp.dao.NotificationService;
import com.erp.dao.ProductService;
import com.erp.dao.UserService;
import com.erp.model.Dealer;
import com.erp.model.Notification;
import com.erp.model.Product;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductService productService;

	@Autowired
	private DealerService builderService;

	@Autowired
	NotificationService notificationService;
	
	
	@Autowired
	UserService userService;

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/product/findAll", method = RequestMethod.GET)
	public DataTablesOutput<Product> getProducts(@Valid DataTablesInput input) {
		return productService.findAll(input);
	}

	@RequestMapping(value = "/product/search", method = RequestMethod.GET)
	public Product search(@RequestParam(name = "prdId") Integer prdId) {
		return productService.findOne(prdId);
	}

	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public List<Product> getProductList(@RequestParam(name = "bldId") Integer bldId) {
		Dealer dealer = builderService.findOne(bldId);
		return productService.findByStaAndDealer('L', dealer);
	}

	@RequestMapping(value = "/product/save", method = RequestMethod.GET)
	public String productEdit(Principal principal, @RequestParam(name = "prdId") Integer prdId,
			@RequestParam(name = "dealerId") Integer dealerId, @RequestParam(name = "prdnme") String prdnme,
			@RequestParam(name = "purAmt") BigDecimal purAmt, @RequestParam(name = "saleAmt") BigDecimal saleAmt,
			@RequestParam(name = "warranty") Short warranty, @RequestParam(name = "sgst") BigDecimal sgst,
			@RequestParam(name = "cgst") BigDecimal cgst, @RequestParam(name = "igst") BigDecimal igst,
			@RequestParam(name = "otherTax") BigDecimal otherTax, @RequestParam(name = "discount") BigDecimal discount,
			@RequestParam(name = "hsnCode") String hsnCode) {
		Product product = null;
		if (prdId == 0)
			product = new Product();
		else {
			product = productService.findOne(prdId);
			logger.info(principal.getName() + " product before:" + product);
		}
		product.setDealer(builderService.findOne(dealerId));
		product.setPrdnme(prdnme);
		product.setPurAmt(purAmt);
		product.setSaleAmt(saleAmt);
		product.setWarranty(warranty);
		product.setSgst(sgst);
		product.setCgst(cgst);
		product.setIgst(igst);
		product.setOtherTax(otherTax);
		product.setDiscount(discount);
		product.setHsnCode(hsnCode);
		Integer audUser = userService.findUserByEmail(principal.getName()).getUsrId();
		product.setAudUsrId(audUser);
		logger.info(principal.getName() + " product  after:" + product);
		productService.save(product);
		
	    notificationService.save(new Notification("Changed "+prdnme, "ADMIN", audUser));
		return "true";
	}

	@RequestMapping(value = "/product/delete", method = RequestMethod.GET)
	public String productDelete(Principal principal, @RequestParam(name = "prdId") Integer prdId) {
		logger.info(principal.getName() + " product delete:" + prdId);
		Integer audUser = userService.findUserByEmail(principal.getName()).getUsrId();
		String name =  productService.findOne(prdId).getPrdnme();
		if (productService.delete(prdId, audUser))
		{
			notificationService.save(new Notification("Deleted Product "+name, "ADMIN", audUser));
			return "true";
		}
		else
			return "false";
	}

	@RequestMapping(value = "/product/sear", method = RequestMethod.GET)
	public List<Product> getProductSearch(@RequestParam(name = "value") String value) {
		return productService.findByPrdnme(value.trim());
	}
}
