package ee.euas.warehouse.backend.items;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemWithDiscount extends Item {
	
	@Setter
	protected int	disc;
	
	protected ItemWithDiscount (String name, double initPrice) {
		super (name, initPrice);
	}
	
	@Override
	public long calculateDiscount () { // per item
		return 10000*(this.disc);
	}
	
	@Override
	public String printInfo () {
		String s="Type - Discounted Item : "+this.toString() +  "<br>";
		s+=", % per pc: " + this.disc + " % \n" + "<br>";
		return s;}
		
}
