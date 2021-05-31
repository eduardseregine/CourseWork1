package ee.euas.warehouse.backend.items;

/*
 * To maintain multi-store approach & type related issues
 */

public abstract class StoreItem {
	
	protected double initPrice;
	
	public static Item getInstance (int type, String name, double initPrice) {
	if (type>0 && type<=5) {
	if (type==5) return new BuyMorePayLess (name, initPrice);
	if (type==4) return new ItemTakeItAll (name, initPrice);
	if (type==3) return new DiscountIndependence (name, initPrice);
	if (type==2) return new ItemWithDiscount (name, initPrice);
	return new Item (name, initPrice);
	}
	return null;
}
	
	public static Item promotionInstance (double netAmount) {
		return new Item (netAmount);
	}

	public int getType () {
		if (this instanceof BuyMorePayLess) return 5;
		if (this instanceof ItemTakeItAll) return 4;
		if (this instanceof DiscountIndependence) return 3;
		if (this instanceof ItemWithDiscount) return 2;
		return 1;
	} 
	
	abstract long calculateDiscount ();
	abstract String printInfo ();
	
//	public void applyStorePremium (int premium) {
//		this.initPrice=this.initPrice*(1+premium/100);		
//	}
	
	public double getInitPrice () {

		return this.initPrice;
	}
		
	
}
