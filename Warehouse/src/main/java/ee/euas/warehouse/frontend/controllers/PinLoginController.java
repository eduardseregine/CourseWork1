package ee.euas.warehouse.frontend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import ee.euas.warehouse.backend.people.Manager;
import ee.euas.warehouse.backend.people.User;
import ee.euas.warehouse.extra.DummyDate;
import ee.euas.warehouse.extra.Initialiser;
import ee.euas.warehouse.frontend.controllers.db.DbRepo;
import ee.euas.warehouse.frontend.controllers.db.Employees;
import ee.euas.warehouse.frontend.validation.Validators;

@Controller
public class PinLoginController extends ControllerCommonData implements WebMvcConfigurer {

	ModelAndView pin1 = new ModelAndView ("login/pin1.html");
	ModelAndView pin2 = new ModelAndView ("login/pin2.html");
	ModelAndView login1 = new ModelAndView ("login/login1.html");
	ModelAndView login2 = new ModelAndView ("login/login2.html");
	
	private ModelAndView menuCashier = new ModelAndView ("cashier/menu.html");
	private ModelAndView menuManager = new ModelAndView ("manager/menu.html");
	private ModelAndView menuCustomer = new ModelAndView ("customer/menu.html");
	
	// PAGE SETTINGS
	
	@Autowired
	public DbRepo repo;
	
	
	

	public void testDb () {
		//repo.save(new Employees (6,"Lets agree this works",300));
		List <Employees> employees = repo.findAll();
		employees.stream().forEach(x->System.out.println(x));
		System.out.println(repo.findById(6).orElse(null));
	
	}
//	
	// PAGE MAPPINGS
	
	@RequestMapping ("/")
	public ModelAndView pinStart () {
	Initialiser.initialise();
	testDb ();
	return pin1;
}
	
	@RequestMapping ("pin_submit")
	public ModelAndView pinSubmit (@RequestParam ("pin") String pinStr) {
	int pin=Validators.toInt (pinStr);
		if (User.isPinExists(pin)) return login1;
	return pin2;
}
	
	@RequestMapping ("login_submit")
	public ModelAndView loginCheck (@RequestParam ("login") String login, @RequestParam ("password") String password) {
	
		User u = User.getLoggedUser(login, password);
		
		if (u!=null) {
		int loggedId = u.getId();
		ControllerCommonData.setUserId(loggedId);
			
		if (u instanceof Manager) return menuManager;
		if (u instanceof Cashier) return menuCashier;
		if (u instanceof Customer) return menuCustomer;
		}		
	return login2;
}		

	@RequestMapping ("pin_back")
	public ModelAndView backToPin () {
	return pin1;
}

	@RequestMapping ("login_back")
	public ModelAndView backToLogin () {
	return login1;
}

	@RequestMapping ("login_back_cashier")
	public ModelAndView backToLoginCashier () {
	cashier.logout();
	return login1;
}
	
		
	@GetMapping("take_time")
	@ResponseBody
	public String getTime() {
	    
		String date="";
		if (DummyDate.getDate().getHour()<10) date+="0";
		date+=DummyDate.getDate().getHour();
		date+=" : ";
		if (DummyDate.getDate().getMinute()<10) date+="0";
		date+=DummyDate.getDate().getMinute();
		
		return date; 
	}
	
	}