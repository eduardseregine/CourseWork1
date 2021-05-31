package ee.euas.warehouse.backend.people;

import lombok.Getter;


@Getter
public class LoyalCustomer extends Customer  {


	protected LoyalCustomer(String name, String surname, String password, String address, String phoneNumber) {
		super(name, surname, password, address, phoneNumber);
	}
	
	
	@Override
	public String toString () {
		String s="LoyalCustomer: [accumBonus:" + this.getBonus()+ super.toString();
		return s;
	}


}
