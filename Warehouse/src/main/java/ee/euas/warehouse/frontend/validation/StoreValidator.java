package ee.euas.warehouse.frontend.validation;

import java.util.Arrays;
import java.util.List;

import ee.euas.warehouse.backend.store.Store;


public class StoreValidator extends Validators {

	final int ERR_FIELDS_NUMBER = 3;
	
	private Store store;
	private List<Boolean> flags;

	public StoreValidator (Store store) {
		this.store = store;
	}

	public StoreValidator() {
	}

	public boolean record (StoreInput istore) {
		
		if (this.store==null) return false;
		
		if (checkSetFlags (istore)) {
		
		//this.store.setName(istore.getName());
		//this.store.setAddress(istore.getAddress());
		if (istore.getType()==2) this.store.setPremium (toInt (istore.getPremium ()));
		return true;}
		
		return false;
	}

	public Store createNew (StoreInput istore) {
		if (checkSetFlags (istore)) {
			this.store= Store.getInstance(istore.getType(), istore.getName(), istore.getAddress()) ;
			if (record (istore)) return this.store;
		}
		
		return null;
	}
	
	private boolean checkSetFlags (StoreInput istore) {
		this.flags=setFlags (Arrays.asList(istore.getName(), istore.getAddress(),istore.getPremium()),
				Arrays.asList(3,3,1));
		if (flags.size()==0) return false;
		if (flags.stream().anyMatch(x->x==true)) return false;
		return true;
	}
	
	
	//Initial data
	
	public String getName() {
		return store.getName();
	}

	public String getAddress() {
		return store.getAddress();
	}

	public int getPremium() {
		return store.getPremium ();
	}
	
	// error messages flags

	public boolean getEname() {
		return getFlag (0);
	}

	public boolean getEaddress() {
		return getFlag (1);
	}

	public boolean getEpremium() {
		return getFlag (2);
	}


	// Service fields
	
	public int getId () {
		return store.getId();
	}
	
	public int getType () {
		return store.getType();
	}
	
	
	private boolean getFlag (int index) {
		if (flags!=null) if (flags.size()>=ERR_FIELDS_NUMBER) return flags.get(index); 
		return false;
	}
	
	@Override
	public String toString () {
		String s="";
		s+=this.getId()+"\n";
		s+=this.getType()+"\n";
		s+=this.getName()+"\n";
		s+=this.getAddress()+"\n";
		s+=this.getPremium()+"\n";
		s+=this.getEname()+"\n";
		s+=this.getEaddress()+"\n";
		s+=this.getEpremium()+"\n";
		
		return s;
	}
}
