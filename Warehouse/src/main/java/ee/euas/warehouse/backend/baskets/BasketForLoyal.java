package ee.euas.warehouse.backend.baskets;

import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import ee.euas.warehouse.extra.DummyDate;
import ee.euas.warehouse.extra.Payable;
import lombok.Getter;

@Getter
public class BasketForLoyal extends Basket implements Payable {

	protected BasketForLoyal (Cashier cashier, Customer customer) {
		super(cashier, customer);
	}

	final int POINTS_FOR_FIVE_EUR = 1;
	final int EUR_FOR_HUNDRED_POINTS = 10;
	

	@Override
	public int getPoints () {
		return (int) (getNetAmount()*this.POINTS_FOR_FIVE_EUR/5);
	}
	
	@Override
	public void pay() {
		if (!isPaid) {
			isPaid=true;
			this.dOfP=DummyDate.getDate();
			// there is no condition that Customer cannot use for 100% of payment
			int newBonus = (int) Math.max(0, this.customer.getBonus()-getNetAmount()); 
			newBonus +=this.calculateBonus();
			this.customer.setBonus(newBonus);
		}

	}
	
	@Override
	int calculateBonus() {
		return getPoints()*this.EUR_FOR_HUNDRED_POINTS/100;
		}	
		
}
