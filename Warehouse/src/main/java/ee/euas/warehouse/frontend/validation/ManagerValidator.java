package ee.euas.warehouse.frontend.validation;

import java.util.Arrays;
import java.util.List;
import ee.euas.warehouse.backend.people.Manager;

public class ManagerValidator extends Validators {

	final int ERR_FIELDS_NUMBER = 7;
	
	private Manager manager;
	private List<Boolean> flags;

	public ManagerValidator (Manager manager) {
		this.manager = manager;
	}

	public ManagerValidator() {
	}

	public boolean record (ManagerInput imanager) {
		
		if (this.manager==null) return false;
		
		if (checkSetFlags (imanager)) {
		
		this.manager.setSurname(imanager.getSurname());	
		this.manager.setName(imanager.getName());
		this.manager.setAddress (imanager.getAddress());
		this.manager.setPhoneNumber (imanager.getPhoneNumber());
		this.manager.setUserName (imanager.getUserName());
		this.manager.setPin (toInt (imanager.getPin()));
		this.manager.setIntPhone(imanager.getIntPhone());
		
		return true;}
		
		return false;
	}

	public Manager createNew (ManagerInput imanager) {
		if (checkSetFlags (imanager) && imanager.getPassword()!="" && !imanager.getPassword().equals("-")) {
			this.manager= Manager.managerInstance (
					imanager.getName(), 
					imanager.getSurname(), 
					imanager.getPassword(), 
					imanager.getAddress(), 
					imanager.getPhoneNumber(), 
					imanager.getIntPhone(), 
					toInt (imanager.getPin())
					);
	 
			this.manager.setUserName (imanager.getUserName());
			return this.manager;
		}
		
		return null;
	}
	
	private boolean checkSetFlags (ManagerInput imanager) {
		this.flags=setFlags (Arrays.asList(
				
				imanager.getSurname(), 
				imanager.getName(), 
				imanager.getAddress(),
				imanager.getPhoneNumber(),
				imanager.getUserName(),
				imanager.getPin(),
				imanager.getIntPhone()),
				 Arrays.asList(3,3,3,7,3,1,1));
		
		
		if (flags.size()==0) return false;
		if (flags.stream().anyMatch(x->x==true)) return false;
		return true;
	}
	
	
	//Initial data
	
	
	public String getSurname () {
		return manager.getSurname();
	}
	
	public String getName() {
		return manager.getName();
	}

	public String getPhoneNumber () {
		return manager.getPhoneNumber ();
	}

	public String getUserName () {
		return manager.getUserName ();
	}

	public String getAddress () {
		return manager.getAddress ();
	}
	
	public int getPin () {
		return manager.getPin ();
	}

	public String getIntPhone () {
		return manager.getIntPhone ();
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
	
	public boolean getEpin () {
		return getFlag (5);
	}
	
	public boolean getEintPhone () {
		return getFlag (6);
	}
	
	public boolean getEpassword () {
		return false;
	}

	// Service fields
	
	public int getId () {
		return this.manager.getId();
	}
	
	
	private boolean getFlag (int index) {
		if (flags!=null) if (flags.size()>=ERR_FIELDS_NUMBER) return flags.get(index); 
		return false;
	}
	
	@Override
	public String toString () {
		String s="";
		s+=this.getId()+"\n";
	    s+=this.getName()+"\n";
		s+=this.getAddress()+"\n";
		s+=this.getEname()+"\n";
		s+=this.getEaddress()+"\n";
				
		return s;
	}
	
}
