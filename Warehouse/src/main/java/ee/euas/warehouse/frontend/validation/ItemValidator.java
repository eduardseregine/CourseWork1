package ee.euas.warehouse.frontend.validation;

import java.util.Arrays;
import java.util.List;

import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.store.Store;


public class ItemValidator extends Validators {


	final int ERR_FIELDS_NUMBER = 2;
	
	private Item item;
	private List<Boolean> flags;

	public ItemValidator (Item item) {
		this.item = item;
	}

	public ItemValidator() {
	}

	public boolean record (ItemInput iitem) {
		
		if (this.item==null) return false;
		
		if (checkSetFlags (iitem)) {
		
		this.item.setName(iitem.getName());
		this.item.setInitPrice(toDouble (iitem.getInitPrice()));
		this.item.setDisc(toInt (iitem.getDisc()));
		this.item.setInd(toInt (iitem.getInd()));
		this.item.setMindisc(toInt (iitem.getMindisc()));
		this.item.setBuyQ(toInt (iitem.getBuyQ()));
		this.item.setForQ(toInt (iitem.getForQ()));
		
		return true;}
		
		return false;
	}

	public Item createNew (ItemInput iitem) {
		if (checkSetFlags (iitem)) {
			this.item=Store.newItemToStores(iitem.getType(),iitem.getName(), toDouble (iitem.getInitPrice())); 
			if (record (iitem)) return this.item;
		}
		
		return null;
	}
	
	private boolean checkSetFlags (ItemInput iitem) {
		this.flags=setFlags (Arrays.asList(iitem.getName(), 
				iitem.getInitPrice()),
				Arrays.asList(3,1));
		if (flags.size()==0) return false;
		if (flags.stream().anyMatch(x->x==true)) return false;
		return true;
	}
	
	
	//Initial data
	
	public String getName() {
		return item.getName();
	}

	public Double getInitPrice () {
		return item.getInitPrice();
	}

	// error messages flags

	public boolean getEname() {
		return getFlag (0);
	}

	public boolean getEinitPrice() {
		return getFlag (1);
	}


	// Service fields
	
	public int getId () {
		return item.getId();
	}
	
	public int getType () {
		return item.getType();
	}
	
	public double getDiscounts () {
		return item.getDiscounts();
	}
	
	public double getNetTotal () {
		return item.getNetTotal();
	}
	
	public int getQty () {
		return item.getQty ();
	}
	

	public int getDisc () {
		return item.getDisc ();
	}
	
	public int getInd () {
		return item.getInd ();
	}
	
	public int getMindisc () {
		return item.getMindisc ();
	}
	
	public int getMaxQ () {
		return item.getMaxQ ();
	}
	
	public int getBuyQ () {
		return item.getBuyQ ();
	}
	
	public int getForQ () {
		return item.getForQ ();
	}
	
	
	private boolean getFlag (int index) {
		if (flags!=null) if (flags.size()>=ERR_FIELDS_NUMBER) return flags.get(index); 
		return false;
	}
	
}
