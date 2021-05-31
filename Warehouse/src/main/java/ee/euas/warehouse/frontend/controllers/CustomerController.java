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

@Controller
public class CustomerController extends ControllerCommonData implements WebMvcConfigurer {

private ModelAndView menuClient = new ModelAndView ("customer/menu.html");
private ModelAndView allBasketsView = new ModelAndView ("customer/allBaskets.html");
private ModelAndView basketView = new ModelAndView ("customer/basketView.html");
private ModelAndView print = new ModelAndView ("customer/print.html");
	

private double totalBaskets=5000;

private List <Item> bitems;
	
private List <Basket> baskets = allbaskets;
private Basket basket;


@ModelAttribute ("total")
public double getTotal () {
	return totalBaskets;}


// PAGE MAPPINGS

@RequestMapping ("client_gomenu")
public ModelAndView clientGoMenu () {
return menuClient;
}

	@RequestMapping ("client_gobaskets")
	public ModelAndView clientGoBaskets () {
	
		updateBaskets ();
		baskets = allbaskets.stream().filter(x->x.getCustomer().getId()==getUserId ()).collect(Collectors.toList());
		totalBaskets=Math.rint((baskets.stream().map(x->x.getTotalAmount()).mapToDouble(x->x).sum())*100)/100;
		
		allBasketsView.addObject("baskets", baskets);
		allBasketsView.addObject("total", totalBaskets);
		
	return allBasketsView;
}

	@RequestMapping ("client_view_basket")
	public ModelAndView managerViewBasket (@RequestParam ("basket_index") int index) {
		basket=baskets.get(index);
		bitems=basket.getBItems();
		basketView.addObject("basket", basket);
		basketView.addObject("bitems", bitems);
		return basketView;
}
	
	@RequestMapping ("client_print_basket")
	public ModelAndView BasketAddItem () {
		
	print.addObject("printInfo", basket);
		
		return print;
	
		
	}
	
	}