package ee.euas.warehouse.backend.baskets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class StoreBasket {
	
	@Setter
	private static List <Basket> baskets=new ArrayList <> ();
	
	protected Cashier cashier;
	protected Customer customer;
	
	

	public static Basket getInstance (Cashier cashier, Customer customer) {
	
	Basket newBasket;
	if (customer.getType()==3) newBasket=new EmployeeBasket (cashier, customer);
	else if (customer.getType()==2) newBasket=new BasketForLoyal (cashier, customer);
	else newBasket=new Basket (cashier, customer);
	baskets.add(newBasket);
	return newBasket;
}

	public int getType () {
		if (this instanceof EmployeeBasket) return 3;
		if (this instanceof BasketForLoyal) return 2;
		return 1;
	} 
	
	public static List <Basket> getBaskets () {
		
		List <Basket> sortedBaskets=baskets.stream()
				.filter(x->!x.isPaid())
				.collect(Collectors.toList());
		sortedBaskets.addAll(baskets.stream()
				.filter(x->x.isPaid())
				.collect(Collectors.toList()));		
		
		return sortedBaskets;
		
		
	}
	
	abstract int calculateBonus (); // calculate bonus to be credited to the customer after basket's payment
	abstract String printInfo ();
	
	
	public String getStoreAddress () {
		return this.cashier.getStoreAddress();
	}
	
	public int getStoreId () {
		return this.cashier.getStoreId();
	}
	
	public String getStoreName () {
		return this.cashier.getStoreName();
	}
	
	public int getCashierId () {
		return this.cashier.getId();
	}
	
	public String getCashierSurname () {
		return this.cashier.getSurname();
	}
	
	public String getClientSurname () {
		return this.customer.getSurname();
	}
}
