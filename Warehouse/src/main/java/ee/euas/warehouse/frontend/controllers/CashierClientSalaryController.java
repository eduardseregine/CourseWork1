package ee.euas.warehouse.frontend.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ee.euas.warehouse.frontend.validation.CustomerInput;
import ee.euas.warehouse.frontend.validation.CustomerValidator;


@Controller
public class CashierClientSalaryController extends ControllerCommonData implements WebMvcConfigurer {

	private ModelAndView clientEdit = new ModelAndView ("cashier/clientEdit.html");
	private ModelAndView salaryView = new ModelAndView ("cashier/salaryView.html");
	

	// PAGE SETTINGS
	
	
	@ModelAttribute ("clchNew")
	public CustomerValidator getNewCustomerValidator () {
	return new CustomerValidator ();}
	
			
	// PAGE MAPPINGS
	
	@RequestMapping ("cashier_goclients")
	public ModelAndView cashierGoClients () {
	return clientEdit;
}
	
	@RequestMapping ("cashier_gosalary")
	public ModelAndView cashierGoSalary () {
	getCashier ();
	salaryView.addObject("cashier", cashier);
	return salaryView;
}
	
	@RequestMapping ("client_add")
	public ModelAndView addClient (@ModelAttribute ("input") CustomerInput cl) {
	
		CustomerValidator clV = new CustomerValidator ();
		
		if (clV.createNew(cl)!=null) clV = new CustomerValidator ();
		updateClients ();
		clientEdit.addObject("clchNew", clV);
		return clientEdit;
	}
	
	
	@RequestMapping ("cashier_pay")
	public ModelAndView cashierPay () {
	cashier.pay();
	salaryView.addObject("cashier", cashier);
	return salaryView;
}
	
	
	}
