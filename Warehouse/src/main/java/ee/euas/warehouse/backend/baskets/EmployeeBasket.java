package ee.euas.warehouse.backend.baskets;

import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import ee.euas.warehouse.extra.DummyDate;
import ee.euas.warehouse.extra.Payable;
import lombok.Getter;

@Getter
public class EmployeeBasket extends Basket implements Payable {

	final int POINTS_FOR_FIFTEEN_EUR = 1;
	final int EUR_FOR_HUNDRED_POINTS = 50;

	
	protected EmployeeBasket (Cashier cashier, Customer customer) {
		super(cashier, customer);		
	}


	@Override
	public int getPoints () {
		return (int) (getNetAmount()*this.POINTS_FOR_FIFTEEN_EUR/15);
	}
	
	@Override
	public void pay() {
		if (!this.isPaid) {
			this.isPaid=true;
			this.dOfP=DummyDate.getDate();
			int newBonus = (int) Math.max(0, this.customer.getBonus()-getNetAmount()); // there is no condition that Customer cannot use for 100% of payment
			newBonus +=this.calculateBonus();
			this.customer.setBonus(newBonus);
		}

	}
	
	@Override
	int calculateBonus() {
		return getPoints()*this.EUR_FOR_HUNDRED_POINTS/100;
		}	
	

	
}
