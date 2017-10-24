package com.aakruth.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aakruth.dao.CreditService;
import com.aakruth.dao.DealerService;
import com.aakruth.dao.DebitService;
import com.aakruth.dao.PurchaseService;
import com.aakruth.dao.RoleService;
import com.aakruth.dao.SaleService;
import com.aakruth.dao.UserService;
import com.aakruth.model.Credit;
import com.aakruth.model.DealerDisplay;
import com.aakruth.model.Debit;
import com.aakruth.model.Ledger;
import com.aakruth.model.PurchaseDisplay;
import com.aakruth.model.RoleTbl;
import com.aakruth.model.SalesDisplay;
import com.aakruth.model.Statement;
import com.aakruth.model.UsrTbl;

@Controller
public class AakruthController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SaleService saleService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private DealerService dealerService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	private DebitService debitService;

	@Autowired
	private CreditService creditService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model, Principal principal) {
		int saleCount = 0, sale = 0, purchaseCount = 0, purchase = 0, customers = 0, builders = 0;
		String email = principal.getName();
		UsrTbl user = userService.findUserByEmail(email);
		RoleTbl role = roleService.findByUsrTbl(user);
		if (role.getRole().equals("ADMIN")) {
			List<PurchaseDisplay> purs = purchaseService.findPurchaseDisplay();
			List<SalesDisplay> sales = saleService.findSalesDisplay();
			List<DealerDisplay> dealers = dealerService.findDealerDisplay();
			for (PurchaseDisplay pur : purs) {
				purchase++;
				purchaseCount += pur.getCnt();
			}
			for (SalesDisplay sal : sales) {
				sale++;
				saleCount += sal.getCnt();
			}
			for (DealerDisplay dealer : dealers) {
				if (dealer.getType() == 'C')
					customers++;
				else if (dealer.getType() == 'B')
					builders++;
			}
		} else {
			List<SalesDisplay> sales = saleService.findSalesDisplay(user);
			for (SalesDisplay sal : sales) {
				sale++;
				saleCount += sal.getCnt();
			}
		}

		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("saleCount", saleCount);
		model.addAttribute("sale", sale);
		model.addAttribute("purchaseCount", purchaseCount);
		model.addAttribute("purchase", purchase);
		model.addAttribute("customers", customers);
		model.addAttribute("builders", builders);
		model.addAttribute("name", name);
		logger.info(name + " views AakruthHome page");
		return "AakruthHome";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String getProduct(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthProduct page");
		return "AakruthProduct";
	}

	@RequestMapping(value = "/builder", method = RequestMethod.GET)
	public String getBuilder(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthBuilder page");
		return "AakruthBuilder";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String getSales(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthSale page");
		return "AakruthSales";
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String getAccounts(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthAccounting page");
		return "AakruthAccounting";
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String getPurchase(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthPurchase page");
		return "AakruthPurchase";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String getCustomer(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthCustomer page");
		return "AakruthCustomer";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthUser page");
		return "AakruthUser";
	}

	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public String getInventory(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(name + " views AakruthInventory page");
		return "AakruthInventory";
	}

	@RequestMapping(value = "/ChangePass", method = RequestMethod.GET)
	public String changePass(ModelMap model) {
		logger.info(" views AakruthChangepass page");
		return "AakruthChangePass";
	}

	@RequestMapping(value = "/ledger", method = RequestMethod.POST)
	public String getLedger(ModelMap model, Principal principal, @RequestParam(name = "dataRange1") String dateRange,
			@RequestParam(name = "dealerId1") Integer dealerId) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		logger.info(name +" views AakruthLedger page");
		String start = dateRange.substring(0, 10);
		String end = dateRange.substring(13);
		List<Ledger> creditLedgers = null;
		List<Ledger> debitLedgers = null;
		List<Ledger> ledgers = null;
		String dealerName = "";
		BigDecimal prevDebitAmt,prevCreditAmt;
		double prevCreditAmount=0.0,prevDebitAmount=0.0,startBalance=0.0;
		creditLedgers = creditService.getLedgers(dealerId, new Date(start), new Date(end));
		debitLedgers = debitService.getLedgers(dealerId, new Date(start), new Date(end));
		ledgers = Stream.concat(creditLedgers.stream(), debitLedgers.stream()).collect(Collectors.toList());
		Collections.sort(ledgers, (a, b) -> b.compareTo(a));
		prevCreditAmt = creditService.findCreditsByDealerAndStrDteBetween(dealerId, new Date("01/01/1991"),new Date(start));
		prevDebitAmt = debitService.findDebitsByDealerAndStrDteBetween(dealerId, new Date("01/01/1991"),new Date(start));
		
		if(prevCreditAmt ==null)
			prevCreditAmount =0.0;
		else
			prevCreditAmount = prevCreditAmt.doubleValue();
		
		if(prevDebitAmt ==null)
			prevDebitAmount =0.0;
		else
			prevDebitAmount = prevDebitAmt.doubleValue();
		startBalance=prevDebitAmount-prevCreditAmount;
		
		dealerName = dealerService.findOne(dealerId).getName();
		model.addAttribute("name", name);
		model.addAttribute("dealerName", dealerName);
		model.addAttribute("ledgers", ledgers);
		model.addAttribute("startBal", startBalance);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "AakruthLedger";
	}

	@RequestMapping(value = "/statement", method = RequestMethod.POST)
	public String getStatement(ModelMap model, Principal principal, @RequestParam(name = "dataRange2") String dateRange,
			@RequestParam(name = "dealerId2") Integer dealerId) {
		String name = principal.getName(); // get logged in username
		name = name.substring(0, name.indexOf("@"));
		model.addAttribute("name", name);
		logger.info(" views AakruthStatement page");
		String start = dateRange.substring(0, 10);
		String end = dateRange.substring(13);
		List<Statement> creditStatement = null;
		List<Statement> debitStatement = null;
		String dealerName = "";
		BigDecimal prevDebitAmt,prevCreditAmt;
		double prevCreditAmount=0.0,prevDebitAmount=0.0,startBalance=0.0;
		System.out.println("printin"+dealerId);
		if(dealerId ==-1){
			creditStatement = creditService.getStmt(new Date(start), new Date(end));
			debitStatement = debitService.getStmt(new Date(start), new Date(end));
			prevCreditAmt = creditService.findCreditsByStrDteBetween(new Date("01/01/1991"),new Date(start));
			prevDebitAmt = debitService.findDebitsByStrDteBetween(new Date("01/01/1991"),new Date(start));
		
		}else{
			creditStatement = creditService.getStmtDealer(dealerId, new Date(start), new Date(end));
			debitStatement = debitService.getStmtDealer(dealerId, new Date(start), new Date(end));
			prevCreditAmt = creditService.findCreditsByDealerAndStrDteBetween(dealerId, new Date("01/01/1991"),new Date(start));
			prevDebitAmt = debitService.findDebitsByDealerAndStrDteBetween(dealerId, new Date("01/01/1991"),new Date(start));
			dealerName = dealerService.findOne(dealerId).getName();
		}
		if(prevCreditAmt ==null)
			prevCreditAmount =0.0;
		else
			prevCreditAmount = prevCreditAmt.doubleValue();
		
		if(prevDebitAmt ==null)
			prevDebitAmount =0.0;
		else
			prevDebitAmount = prevDebitAmt.doubleValue();
		startBalance=prevDebitAmount-prevCreditAmount;
		
		
		model.addAttribute("name", name);
		model.addAttribute("dealerName", dealerName);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("credits", creditStatement);
		model.addAttribute("debits", debitStatement);
		model.addAttribute("startBal", startBalance);
		return "AakruthStatement";
	}

}
