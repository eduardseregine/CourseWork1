package ee.euas.warehouse.frontend.controllers;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import ee.euas.warehouse.backend.baskets.Basket;
import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import ee.euas.warehouse.backend.people.Manager;
import ee.euas.warehouse.backend.people.User;
import ee.euas.warehouse.backend.store.Store;
import ee.euas.warehouse.extra.DummyDate;
import lombok.Getter;
import lombok.Setter;

@Controller
public abstract class ControllerCommonData {

	@Getter
	@Setter
	private static int userId;
	
	protected static List <Store> stores;
	protected static List <Item> itemsManager;
	protected static List <Item> itemsCashier;
	protected static List <Cashier> cashiers;
	protected static List <Customer> clients;
	protected static List <Basket> allbaskets;
	protected static List <Manager> allmanagers;
	
	protected static Manager manager;
	protected static Cashier cashier;
	protected static Customer customer;
	
	
		
	@ModelAttribute ("today1")
	public static Date today () {
		Date today=Date.from(DummyDate.getDate().atZone(ZoneId.systemDefault()).toInstant());
		return today;}


	@ModelAttribute ("stores")
	public static List <Store> displayStores () {
	updateStores ();
		return stores;}	
	
	
	@ModelAttribute ("clients")
	public List <Customer> displayClients () {
		updateClients ();
	return clients;}
	
	@ModelAttribute ("cashiers")
	public List <Cashier> displayCashiers () {
		updateCashiers ();
		return cashiers;}
	
	@ModelAttribute ("manager")
	public static Manager displayManager () {
		getManager ();	
		return manager;}
	
	@ModelAttribute ("cashier")
	public static Cashier displayCashier () {
		getCashier ();
		return cashier;}

	@ModelAttribute ("client")
	public static Customer displayClient () {
		getCustomer ();
		return customer;}
	

	protected static Manager getManager() {
	
		if (User.getManagers()!=null) {
		manager=User.getManagers().stream()
				.filter(x->x.getId()==userId)
				.findFirst().orElse(null);}
		
				return manager;
	}
	
	protected static Cashier getCashier() {
		
		if (User.getCashiers()!=null) {
		cashier=User.getCashiers().stream()
				.filter(x->x.getId()==userId)
				.findFirst().orElse(null);}
		return cashier;
	}

	protected static Customer getCustomer () {
		
		if (User.getCustomers()!=null) {
		customer=User.getCustomers().stream()
				.filter(x->x.getId()==userId)
				.findFirst().orElse(null);}
		
				return customer;
	}
	
	protected static void updateStores () {
		stores=Store.getAllStores();
	}
	
	protected static void updateItemsManager () {
		itemsManager=Store.getItems();
	}
	
	protected static void updateItemsCashier () {
		itemsCashier=cashier.getItems();
	}
	
	protected static void updateCashiers () {
		cashiers=User.getCashiers();
	}

	protected static void updateClients () {
		clients=User.getCustomers();
	}
	
	protected static void updateBaskets () {
		allbaskets=Basket.getBaskets();					
	}
	
	protected static void updateManagers () {
		allmanagers=User.getManagers();
	}
}
