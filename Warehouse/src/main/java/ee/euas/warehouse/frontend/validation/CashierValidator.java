package ee.euas.warehouse.frontend.validation;

import java.util.Arrays;
import java.util.List;

import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.store.Store;

public class CashierValidator extends Validators {

	
	final int ERR_FIELDS_NUMBER = 7;
	
	private Cashier cashier;
	private List<Boolean> flags;

	public CashierValidator (Cashier cashier) {
		this.cashier = cashier;
	}

	public CashierValidator() {
	}

	public boolean record (CashierInput icashier) {
		
		if (this.cashier==null) return false;
		
		if (checkSetFlags (icashier)) {
		
		this.cashier.setSurname(icashier.getSurname());	
		this.cashier.setName(icashier.getName());
		this.cashier.setAddress (icashier.getAddress());
		this.cashier.setPhoneNumber (icashier.getPhoneNumber());
		this.cashier.setUserName (icashier.getUserName());
		this.cashier.setBaseSalary(toDouble (icashier.getBaseSalary()));
		this.cashier.setIntPhone(icashier.getIntPhone());
		
		return true;}
		
		return false;
	}

	public Cashier createNew (CashierInput icashier) {
			
		if (checkSetFlags (icashier) && icashier.getPassword()!="" && icashier.getPassword()!="-" && Store.getStoreById(icashier.getStoreId())!=null) {
			this.cashier= Cashier.cashierInstance (Store.getStoreById(icashier.getStoreId()),  icashier.getName(), icashier.getSurname(), 
												icashier.getPassword(), icashier.getAddress(), 
												icashier.getPhoneNumber(), 
												icashier.getIntPhone(),
												toDouble (icashier.getBaseSalary())
					);
			this.cashier.setUserName (icashier.getUserName());
			
			return this.cashier;
		}
		
		return null;
	}
	
	private boolean checkSetFlags (CashierInput icashier) {
		this.flags=setFlags (Arrays.asList(
				
				icashier.getSurname(), 
				icashier.getName(), 
				icashier.getAddress(),
				icashier.getPhoneNumber(),
				icashier.getUserName(),
				icashier.getIntPhone(),
				icashier.getBaseSalary()),
				 Arrays.asList(3,3,3,7,3,1,1));
		
		
		if (flags.size()==0) return false;
		if (flags.stream().anyMatch(x->x==true)) return false;
		return true;
	}
	
	
	//Initial data
	
	
	public String getSurname () {
		return cashier.getName();
	}
	
	public String getName() {
		return cashier.getName();
	}

	public String getPhoneNumber () {
		return cashier.getPhoneNumber ();
	}

	public String getUserName () {
		return cashier.getUserName ();
	}


	public String getIntPhone () {
		return cashier.getIntPhone ();
	}

	public double getBaseSalary () {
		return cashier.getBaseSalary ();
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
	
	public boolean getEintPhone () {
		return getFlag (5);
	}
	
	public boolean getEbaseSalary () {
		return getFlag (6);
	}
	
	
	// Service fields
	
	public int getId () {
		return cashier.getId();
	}
	
	public int getStoreId () {
		return cashier.getStore().getId();
	}

	public String getStoreAddress () {
		return cashier.getStore().getAddress();
	}
	
	public String getStoreName () {
		return cashier.getStore().getName();
	}
	
	
	
	private boolean getFlag (int index) {
		if (flags!=null) if (flags.size()>=ERR_FIELDS_NUMBER) return flags.get(index); 
		return false;
	}
	
}
