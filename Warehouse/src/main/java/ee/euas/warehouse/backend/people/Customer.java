package ee.euas.warehouse.backend.people;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Customer extends User {

	@Setter
	private int bonus;	
	
	protected Customer(String name, String surname, String password, String address, String phoneNumber) {
		super(name, surname, password, address, phoneNumber);
	}


	public int getType () {
		if (this instanceof LoyalCustomer) return 2;
		if (this instanceof EmployeeCustomer) return 3;
		return 1;
	}
	
	@Override
	void login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString () {
		if (!(this instanceof LoyalCustomer) && !(this instanceof EmployeeCustomer)) return "Customer (Ordinary): " +super.toString();
		return super.toString();
	}
	
}
