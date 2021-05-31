package ee.euas.warehouse.backend.people;

import ee.euas.warehouse.extra.Payable;
import lombok.Getter;

@Getter
public class EmployeeCustomer extends Customer implements Payable {


	
	protected EmployeeCustomer(String name, String surname, String password, String address, String phoneNumber) {
		super(name, surname, password, address, phoneNumber);
	}


	@Override
	public String toString () {
		String s="EmployeeCustomer: [accumBonus:" + this.getBonus() +"] "+ super.toString();
		return s;
	}


	@Override
	public void pay() {
		// Smth to Do, not specified in the assignment
		
	}
	
}
