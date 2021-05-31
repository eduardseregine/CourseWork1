package ee.euas.warehouse.frontend.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ee.euas.warehouse.backend.baskets.Basket;
import ee.euas.warehouse.backend.items.Item;


@Controller
public class ManagerBasketController extends ControllerCommonData implements WebMvcConfigurer {

	private ModelAndView menu = new ModelAndView ("manager/menu.html");
	private ModelAndView allBasketsView = new ModelAndView ("manager/allBaskets.html");
	private ModelAndView basketView = new ModelAndView ("manager/basketView.html");
	private ModelAndView print = new ModelAndView ("manager/print.html");
	
	private double totalBaskets=5000;
	
	private List <Item> bitems;
		
	private List <Basket> baskets = allbaskets;
	private Basket basket;

	
	@ModelAttribute ("total")
	public double getTotal () {
		return totalBaskets;}

	
	// PAGE MAPPINGS
	
	@RequestMapping ("manager_gomenu")
	public ModelAndView managerGoMenu () {
	return menu;
}

	@RequestMapping ("manager_gobaskets")
	public ModelAndView managerGoBaskets () {
	
		updateBaskets ();
		baskets = allbaskets;
		totalBaskets=Math.rint ((baskets.stream().map(x->x.getTotalAmount()).mapToDouble(x->x).sum())*100)/100;
		
		allBasketsView.addObject("baskets", baskets);
		allBasketsView.addObject("total", totalBaskets);
		
	return allBasketsView;
}

	@RequestMapping ("manager_view_basket")
	public ModelAndView managerViewBasket (@RequestParam ("basket_index") int index) {
		basket=baskets.get(index);
		bitems=basket.getBItems();
		customer=basket.getCustomer();
		basketView.addObject("basket", basket);
		basketView.addObject("bitems", bitems);
		basketView.addObject("client", customer);
		return basketView;
}
	
	@RequestMapping ("manager_print_basket")
	public ModelAndView BasketAddItem () {
		
	print.addObject("printInfo", basket);
		
		return print;
	
		
	}
	
}
