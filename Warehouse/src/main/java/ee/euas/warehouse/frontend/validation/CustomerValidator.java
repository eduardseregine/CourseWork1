package ee.euas.warehouse.frontend.validation;

import java.util.Arrays;
import java.util.List;

import ee.euas.warehouse.backend.people.Customer;

public class CustomerValidator extends Validators {

	final int ERR_FIELDS_NUMBER = 5;
	
	private Customer customer;
	private List<Boolean> flags;

	public CustomerValidator (Customer customer) {
		this.customer = customer;
	}

	public CustomerValidator() {
	}

	public boolean record (CustomerInput icustomer) {
		
		if (this.customer==null) return false;
		
		if (checkSetFlags (icustomer)) {
		
		this.customer.setSurname(icustomer.getSurname());	
		this.customer.setName(icustomer.getName());
		this.customer.setAddress (icustomer.getAddress());
		this.customer.setPhoneNumber (icustomer.getPhoneNumber());
		this.customer.setUserName (icustomer.getUserName());
		
		
		return true;}
		
		return false;
	}

	public Customer createNew (CustomerInput icustomer) {
		if (checkSetFlags (icustomer) && icustomer.getPassword()!="" && icustomer.getPassword()!="-") {
			this.customer= Customer.customerInstance (icustomer.getType(),icustomer.getName(), icustomer.getSurname(), 
					icustomer.getPassword(), icustomer.getAddress(), 
					icustomer.getPhoneNumber());
			this.customer.setUserName (icustomer.getUserName());
		
			return this.customer;
		}
		
		return null;
	}
	
	private boolean checkSetFlags (CustomerInput icustomer) {
		this.flags=setFlags (Arrays.asList(
				
				icustomer.getSurname(), 
				icustomer.getName(), 
				icustomer.getAddress(),
				icustomer.getPhoneNumber(),
				icustomer.getUserName() ),
				 Arrays.asList(3,3,3,7,3));
		
		
		if (flags.size()==0) return false;
		if (flags.stream().anyMatch(x->x==true)) return false;
		return true;
	}
	
	
	//Initial data
	
	
	public String getSurname () {
		return customer.getName();
	}
	
	public String getName() {
		return customer.getName();
	}

	public String getAddress () {
		return customer.getAddress ();
	}
	
	public String getPhoneNumber () {
		return customer.getPhoneNumber ();
	}

	public String getUserName () {
		return customer.getUserName ();
	}

	
	// error messages flags

	public boolean getEsurname () {
		return getFlag (0);
	}
	
	public boolean getEname() {
		return getFlag (1);
	}

	public boolean getEaddress() {
		return getFlag (2);
	}

	public boolean getEphoneNumber () {
		return getFlag (3);
	}

	public boolean getEuserName () {
		return getFlag (4);
	}
	
	public boolean getEpassword () {
		return false;
	}
	
	// Service fields
	
	public int getId () {
		return customer.getId();
	}
	
	public int getType () {
		return customer.getType();
	}
	
	public int getBonus () {
		return customer.getBonus();
	}
	
	private boolean getFlag (int index) {
		if (flags!=null) if (flags.size()>=ERR_FIELDS_NUMBER) return flags.get(index); 
		return false;
	}
	
}
