package com.erp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * TilesController that demonstrates tiles mapping, reguest parameters and path variables.
 * 
 * @author Mark Meany
 */
@Controller
public class TilesController {
	private Log log = LogFactory.getLog(this.getClass());
    
    @RequestMapping(value = "/home", method=RequestMethod.GET)
	public String dash() {
	    return "site.home";
	}
	
    @RequestMapping(value = "/product", method=RequestMethod.GET)
	public String product() {
	    return "site.product";
	}
    
    @RequestMapping(value = "/builder", method=RequestMethod.GET)
   	public String builder() {
   	    return "site.builder";
   	}
    
    @RequestMapping(value = "/client", method=RequestMethod.GET)
   	public String client() {
   	    return "site.client";
   	}
    @RequestMapping(value = "/saleOrder", method=RequestMethod.GET)
   	public String saleOrder() {
   	    return "site.saleOrder";
   	}
}
