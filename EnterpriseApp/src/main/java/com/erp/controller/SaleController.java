package com.erp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.PdfService;
import com.erp.dao.ProductService;
import com.erp.dao.PurchaseService;
import com.erp.dao.RoleService;
import com.erp.dao.SaleBillService;
import com.erp.dao.SaleService;
import com.erp.dao.UserService;
import com.erp.model.Product;
import com.erp.model.ProductChart;
import com.erp.model.Role;
import com.erp.model.Sale;
import com.erp.model.SaleBill;
import com.erp.model.SaleChart;
import com.erp.model.SalesDisplay;
import com.erp.model.User;

@RestController
public class SaleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SaleBillService billService;

	@Autowired
	SaleService saleService;

	@Autowired
	ProductService productService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PdfService pdfService;

	@RequestMapping(value = "/sale/list")
	public List<Sale> saleList(@RequestParam(name = "billId") Integer billId) {
		return saleService.findBySaleBill(billId);
	}

	@RequestMapping(value = "/bill/print", method = RequestMethod.GET)
	public void pdf(@RequestParam(name = "billId") Integer billId, HttpServletResponse response) {
		File file = pdfService.print(billId);
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + file);
			response.setContentLength((int) file.length());

			FileInputStream fileInputStream = new FileInputStream(file);
			OutputStream responseOutputStream = response.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/sale/edit", method = RequestMethod.GET)
	public String saleEdit(Principal principal, @RequestParam(name = "salId") Integer salId,
			@RequestParam(name = "cnt") Integer cnt, @RequestParam(name = "amt") BigDecimal amt) {
		Sale sale = saleService.findOne(salId);
		int existingCnt = sale.getCnt();
		int reqCnt = cnt - existingCnt;
		int prdId = sale.getProduct().getPrdId();
		int availability = purchaseService.getPurchaseProductCount(prdId) - saleService.getSaleProductCount(prdId);
		if ((availability - reqCnt) >= 0) {
			logger.info(principal.getName() + " sale edit before:" + sale);
			sale.setCnt(cnt);
			sale.setSaleAmt(amt);
			sale.setAudUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
			logger.info(principal.getName() + "sale edit after:" + sale);
			if (saleService.edit(sale))
				return "true";
			else
				return "You cannot edit this Sale right now!";
		} else {
			return "Only " + availability + " items available in stock";
		}

	}

	@RequestMapping(value = "/sale/save", method = RequestMethod.GET)
	public String saleSave(Principal principal, @RequestParam(name = "prdId") Integer prdId,
			@RequestParam(name = "billId") Integer billId, @RequestParam(name = "cnt") Integer cnt,
			@RequestParam(name = "amt") BigDecimal amt, @RequestParam(name = "discount") BigDecimal discount) {
		Product product = productService.findOne(prdId);
		SaleBill bill = billService.findOne(billId);
		Sale sale = new Sale();
		sale.setCnt(cnt);
		sale.setSaleAmt(amt);
		sale.setSaleBill(bill);
		sale.setDiscount(discount);
		sale.setCgst(product.getCgst());
		sale.setIgst(product.getIgst());
		sale.setSgst(product.getSgst());
		sale.setOtherTax(product.getOtherTax());
		sale.setProduct(product);
		sale.setAudUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
		logger.info(principal.getName() + " sale save:" + sale);
		if (saleService.save(sale))
			return "true";
		else
			return "You cannot save this Sale right now!";
		/*
		 * int availability = purchaseService.getPurchaseProductCount(prdId) -
		 * saleService.getSaleProductCount(prdId); if ((availability - cnt) >=
		 * 0) { Product product = productService.findOne(prdId); SaleBill bill =
		 * billService.findOne(billId); Sale sale = new Sale();
		 * sale.setCnt(cnt); sale.setSaleAmt(amt); sale.setSaleBill(bill);
		 * sale.setProduct(product);
		 * sale.setAudUsrId(userService.findUserByEmail(principal.getName()).
		 * getUsrId()); logger.info(principal.getName()+" sale save:" + sale);
		 * if (saleService.save(sale)) return "true"; else return
		 * "You cannot save this Sale right now!";
		 * 
		 * }else{ return "Only " + availability + " items available in stock"; }
		 */
	}

	@RequestMapping(value = "/sale/delete", method = RequestMethod.GET)
	public String saleDelete(Principal principal, @RequestParam(name = "salId") Integer salId) {
		logger.info(principal.getName() + " sale delete:" + salId);
		if (saleService.delete(salId, userService.findUserByEmail(principal.getName()).getUsrId()))
			return "true";
		else
			return "false";
	}

	@RequestMapping(value = "/sale/search", method = RequestMethod.GET)
	public Sale getSale(@RequestParam(name = "salId") Integer salId) {
		return saleService.findOne(salId);
	}

	@RequestMapping(value = "/sale/saleChart", method = RequestMethod.GET)
	public List<SaleChart> getSaleChart(Principal principal) {

		String email = principal.getName();
		User user = userService.findUserByEmail(email);
		Role role = roleService.findByUser(user);
		if (role.getRole().equals("ADMIN"))
			return saleService.findSaleChart();
		else
			return saleService.findSaleChart(user);
	}

	@RequestMapping(value = "/sale/productChart", method = RequestMethod.GET)
	public List<ProductChart> getProductChart(Principal principal) {
		String email = principal.getName();
		User user = userService.findUserByEmail(email);
		Role role = roleService.findByUser(user);
		if (role.getRole().equals("ADMIN"))
			return saleService.findProductChart();
		else
			return saleService.findProductChart(user);
	}

	@RequestMapping(value = "/sale/saleDisplay", method = RequestMethod.GET)
	public List<SalesDisplay> getSaleDisplay(Principal principal) {
		String email = principal.getName();
		User user = userService.findUserByEmail(email);
		Role role = roleService.findByUser(user);
		if (role.getRole().equals("ADMIN"))
			return saleService.findSalesDisplay();
		else
			return saleService.findSalesDisplay(user);

	}

}
