package ee.euas.warehouse.backend.items;

import lombok.Getter;
import lombok.Setter;


@Getter
public class BuyMorePayLess extends Item {

	final int	maxQ=10;
	@Setter
	protected int	buyQ=0; // larger qty (how many to buy)
	@Setter
	protected int	forQ=0; // smaller qty, for how many to pay
	
	protected BuyMorePayLess (String name, double initPrice) {
		super (name, initPrice);
	}
	
	@Override
	public long calculateDiscount () { // % per item
	    if (this.getQty()<buyQ || forQ<=0 || buyQ<=1 || buyQ<=forQ) return 0;
		int qtyDisc=this.getQty();
		if (qtyDisc>maxQ) qtyDisc=maxQ;
		int sets =qtyDisc/buyQ;
		int unpaidQty = (buyQ-forQ) * (sets);
		return (long) Math.rint ((10000*100*unpaidQty/this.getQty())*100)/100;
	}
	
	@Override	
	public String printInfo () {
		String s="Type - BuyMorePayLess: " + this.toString()+ "<br>";
		s+="--, can buy: " + this.buyQ+" pcs for the price of " + this.forQ + "pcs";
		s+="----Max to buy "+ this.maxQ + "\n"+ "<br>";
		return s;
	}
	
}
