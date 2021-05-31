package ee.euas.warehouse.frontend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ee.euas.warehouse.backend.baskets.Basket;
import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.people.Customer;

@Controller
public class CashierBasketController extends ControllerCommonData implements WebMvcConfigurer {

	private ModelAndView menu = new ModelAndView ("cashier/menu.html");
	private ModelAndView allBaskets = new ModelAndView ("cashier/allBaskets.html");
	private ModelAndView basketEdit = new ModelAndView ("cashier/basketEdit.html");
	private ModelAndView print = new ModelAndView ("cashier/print.html");
	private double totalBaskets=5000;
		
	private List <Item> bitems;
		
	private List <Basket> baskets = allbaskets;
	private Basket basket;
	private Customer customer;
	
	@ModelAttribute ("baskets")
	public List <Basket> displayBaskets () {
		updateBaskets ();
		return baskets;}


	// PAGE SETTINGS
	
	@ModelAttribute ("client")
	public Customer getBasketClient () {
		if (basket!=null)
		customer=basket.getCustomer();
		return customer;}
	
	@ModelAttribute ("total")
	public double getTotal () {
		return totalBaskets;}
	

	@ModelAttribute ("bitems")
	public List <Item> updateBasketItems () {
		if (basket!=null)
		bitems=basket.getBItems();
		return bitems;}
	
	// PAGE MAPPINGS
	
	@RequestMapping ("cashier_gomenu")
	public ModelAndView cashierGoMenu () {
	return menu;
}

	@RequestMapping ("basket_view")
	public ModelAndView viewBasket (@RequestParam ("basket_index") int index) {
		basket=baskets.get(index);
		bitems=basket.getBItems();
		customer=basket.getCustomer();
		itemsCashier=cashier.getItems();
		basketEdit.addObject("basket", basket);
		basketEdit.addObject("bitems", bitems);
		basketEdit.addObject("client", customer);
		basketEdit.addObject("items", itemsCashier);
		return basketEdit;
}


	@RequestMapping ("cashier_viewall")
	public ModelAndView cashierViewAll () {
		
		baskets = allbaskets.stream().filter(x->x.getCashierId()==getUserId ()).collect(Collectors.toList());
		totalBaskets=Math.rint((baskets.stream().map(x->x.getTotalAmount()).mapToDouble(x->x).sum())*100)/100;
		
		allBaskets.addObject("baskets", baskets);
		allBaskets.addObject("total", totalBaskets);
		
		
		return allBaskets;
}
	
	
	@RequestMapping ("viewbasket")
	public ModelAndView cashierViewClientBaskets (@RequestParam ("viewbasket_index") int index) {
		
		int clientId = clients.get(index).getId();
		
		baskets = allbaskets.stream().filter(x->x.getCashierId()==getUserId () && x.getCustomer().getId()==clientId).collect(Collectors.toList());
		totalBaskets=Math.rint((baskets.stream().map(x->x.getTotalAmount()).mapToDouble(x->x).sum())*100)/100;
		
		allBaskets.addObject("baskets", baskets);
		allBaskets.addObject("total", totalBaskets);
		
			
		return allBaskets;
}
	

	@RequestMapping ("addbasket")
	public ModelAndView cashierViewNewBasket (@RequestParam ("addnewbasket_index") int index) {
		
		customer = clients.get(index);
		basket=Basket.getInstance(cashier, customer);
		updateBaskets();
		updateBasketItems ();
		itemsCashier=cashier.getItems();
		bitems=basket.getBItems();
		
		basketEdit.addObject("basket", basket);
		basketEdit.addObject("client", customer);
		basketEdit.addObject("items", itemsCashier);
		basketEdit.addObject("bitems", bitems);
		return basketEdit;

}
	
	@RequestMapping ("basket_applydiscount")
	public ModelAndView basketApplyDiscount () {
		
		basket.applyPromotion();
				
		basketEdit.addObject("basket", basket);
		return basketEdit;

}
	
	@RequestMapping ("basket_pay")
	public ModelAndView basketPay () {
		basket.pay();
				
		basketEdit.addObject("basket", basket);
		return basketEdit;

}
	
	@RequestMapping ("bitem_decrement")
	public ModelAndView BasketDecrementItem (@RequestParam ("decrement_index") int index) {
		
		basket.decrement(index);
		updateBasketItems ();
		
		basketEdit.addObject("basket", basket);
		basketEdit.addObject("bitems", bitems);
		return basketEdit;

}
	@RequestMapping ("bitem_increment")
	public ModelAndView BasketIncrementItem (@RequestParam ("increment_index") int index) {
		
		basket.increment(index);
		updateBasketItems ();		
		basketEdit.addObject("basket", basket);
		basketEdit.addObject("bitems", bitems);
		
		return basketEdit;

}
	
	@RequestMapping ("basket_item_addnew")
	public ModelAndView BasketAddItem (@RequestParam ("add_index") int index) {
		
		Item it=itemsCashier.get(index);
		basket.add(it);
		
		updateBasketItems ();		
		basketEdit.addObject("basket", basket);
		basketEdit.addObject("bitems", bitems);
		
		return basketEdit;

}
	
	@RequestMapping ("basket_print")
	public ModelAndView BasketAddItem () {
		
	print.addObject("printInfo", basket);
		
		return print;

}
	
	}
