package ee.euas.warehouse.backend.items;

import lombok.Getter;
import lombok.Setter;

@Getter
public class DiscountIndependence extends ItemWithDiscount {
	@Setter
	protected int	ind;
	
	protected DiscountIndependence (String name, double initPrice) {
		super (name, initPrice);
	}
	
	@Override
	public long calculateDiscount () { // per item
		return super.calculateDiscount()+(this.ind)*10000;
	}
	
	@Override
	public String printInfo () {
		String s="Type - Discount Independence : "+ this.toString()+ "<br>";
		s+="--, % per pc: " + super.getDisc() + " Independence Disc % per pc: " + this.ind + "\n"+ "<br>";
		return s;
	}
	
}
