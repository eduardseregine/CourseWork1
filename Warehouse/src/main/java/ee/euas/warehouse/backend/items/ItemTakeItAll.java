package ee.euas.warehouse.backend.items;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemTakeItAll extends ItemWithDiscount {

	@Setter
	protected int	mindisc;

	protected ItemTakeItAll (String name, double initPrice) {
		super (name, initPrice);
	}
	
	@Override
	public long calculateDiscount () { // per item
		if (super.getQty ()<mindisc) return 0;
		return super.calculateDiscount();
	}
	
	@Override
	public String printInfo () {
		String s="Type - TakeItAll Item : "+this.toString() + "<br>";
		s+="--Type : ItemTakeItAll, % per pc: " + super.getDisc() + " %, if at least " + this.getMindisc()+ " pcs" + "\n"+ "<br>";
		return s;
	}
	
	
}
