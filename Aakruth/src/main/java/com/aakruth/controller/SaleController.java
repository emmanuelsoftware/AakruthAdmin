package com.aakruth.controller;

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

import com.aakruth.dao.BillService;
import com.aakruth.dao.PdfService;
import com.aakruth.dao.ProductService;
import com.aakruth.dao.PurchaseService;
import com.aakruth.dao.RoleService;
import com.aakruth.dao.SaleService;
import com.aakruth.dao.UserService;
import com.aakruth.model.BillTbl;
import com.aakruth.model.PrdTbl;
import com.aakruth.model.ProductChart;
import com.aakruth.model.RoleTbl;
import com.aakruth.model.SalTbl;
import com.aakruth.model.SaleChart;
import com.aakruth.model.SalesDisplay;
import com.aakruth.model.UsrTbl;

@RestController
public class SaleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BillService billService;

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

	@RequestMapping(value = "/sale/list", method = RequestMethod.POST)
	public List<SalTbl> saleList(@RequestParam(name = "billId") Integer billId) {
		return saleService.findByBillTbl(billId);
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
	public String saleEdit(Principal principal,@RequestParam(name = "salId") Integer salId, @RequestParam(name = "cnt") Integer cnt,
			@RequestParam(name = "amt") BigDecimal amt) {
		SalTbl sale = saleService.findOne(salId);
		int existingCnt = sale.getCnt();
		int reqCnt = cnt - existingCnt;
		int prdId = sale.getPrdTbl().getPrdId();
		int availability = purchaseService.getPurchaseProductCount(prdId) - saleService.getSaleProductCount(prdId);
		if ((availability - reqCnt) >= 0) {
			logger.info(principal.getName()+" sale edit before:" + sale);
			sale.setCnt(cnt);
			sale.setAmt(amt);
			sale.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
			logger.info(principal.getName()+"sale edit after:" + sale);
			if (saleService.edit(sale))
				return "true";
			else
				return "You cannot edit this Sale right now!";
		} else {
			return "Only " + availability + " items available in stock";
		}

	}

	@RequestMapping(value = "/sale/save", method = RequestMethod.GET)
	public String saleSave(Principal principal,@RequestParam(name = "prdId") Integer prdId, @RequestParam(name = "billId") Integer billId,
			@RequestParam(name = "cnt") Integer cnt, @RequestParam(name = "amt") BigDecimal amt) {
		int availability = purchaseService.getPurchaseProductCount(prdId) - saleService.getSaleProductCount(prdId);
		if ((availability - cnt) >= 0) {
			PrdTbl product = productService.findOne(prdId);
			BillTbl bill = billService.findOne(billId);
			SalTbl sale = new SalTbl();
			sale.setCnt(cnt);
			sale.setAmt(amt);
			sale.setBillTbl(bill);
			sale.setPrdTbl(product);
			sale.setUsrId(userService.findUserByEmail(principal.getName()).getUsrId());
			logger.info(principal.getName()+" sale save:" + sale);
			if (saleService.save(sale))
				return "true";
			else
				return "You cannot save this Sale right now!";

		}else{
			return "Only " + availability + " items available in stock";
		}
	}

	@RequestMapping(value = "/sale/delete", method = RequestMethod.GET)
	public String saleDelete(Principal principal,@RequestParam(name = "salId") Integer salId) {
		logger.info(principal.getName()+" sale delete:" + salId);
		if (saleService.delete(salId,userService.findUserByEmail(principal.getName()).getUsrId()))
			return "true";
		else
			return "false";
	}

	@RequestMapping(value = "/sale/saleChart", method = RequestMethod.GET)
	public List<SaleChart> getSaleChart(Principal principal) {

		String email = principal.getName();
		UsrTbl user = userService.findUserByEmail(email);
		RoleTbl role = roleService.findByUsrTbl(user);
		if (role.getRole().equals("ADMIN"))
			return saleService.findSaleChart();
		else
			return saleService.findSaleChart(user);
	}

	@RequestMapping(value = "/sale/productChart", method = RequestMethod.GET)
	public List<ProductChart> getProductChart(Principal principal) {
		String email = principal.getName();
		UsrTbl user = userService.findUserByEmail(email);
		RoleTbl role = roleService.findByUsrTbl(user);
		if (role.getRole().equals("ADMIN"))
			return saleService.findProductChart();
		else
			return saleService.findProductChart(user);
	}

	@RequestMapping(value = "/sale/saleDisplay", method = RequestMethod.GET)
	public List<SalesDisplay> getSaleDisplay(Principal principal) {
		String email = principal.getName();
		UsrTbl user = userService.findUserByEmail(email);
		RoleTbl role = roleService.findByUsrTbl(user);
		if (role.getRole().equals("ADMIN"))
			return saleService.findSalesDisplay();
		else
			return saleService.findSalesDisplay(user);

	}

}
