package ee.euas.warehouse.backend.items;

import lombok.Getter;
import lombok.Setter;


@Getter
public class Item extends StoreItem {

	final double CHRISTMAS_PROM_DISCOUNT=0.1;
	
	protected int	id;
	@Setter
	protected String	name;
	
	private int	qty;
	
	private static int counter = 0;
	
	protected Item(String name, double initPrice) {
		this.name=name;
		this.initPrice=initPrice;
		counter++; id=counter;
		qty=0;
	}
	
	protected Item (double netAmount) { // Christmas Promotion
		this.id=0;
		this.name="**Christmas Promotion Discount***";
		this.qty=1;
		this.initPrice=Math.rint ((-1)*netAmount * CHRISTMAS_PROM_DISCOUNT*100)/100;
	}
	
	public void setInitPrice (double price) {
		if (this.id!=0) this.initPrice=price;
	}
	
	public void setQty (int qty) {
		if (this.id!=0) this.qty=qty;
	}
	
	public int getDisc () {return 0;}
	public int getInd () {return 0;}
	public int getMindisc () {return 0;}

	public int getMaxQ () {return 0;}
	public int getBuyQ () {return 0;}
	public int getForQ () {return 0;}
	
	public double getDiscounts () { // (1) 100/100 - for rounding; (2) /100 - for convert from percent; (3) /10000 - to eliminate rounding effect at BuyMorePayLess
		return Math.rint (100*(this.calculateDiscount ()*this.getInitPrice()*this.qty)/100/10000)/100;
	}
	
	public double getNetTotal () {
		return Math.rint((this.getInitPrice()*this.qty-this.getDiscounts())*100)/100;
	}
	
	
	public long calculateDiscount () { // % per item
		return 0;
	}
	
	
	public String printInfo () {
		return "Type - Standard Item : "+ this.toString() + "<br>";
		}

	public void setInd(int disc) {}
	public void setDisc (int disc) {}
	public void setMindisc (int qty) {}
	public void setBuyQ (int qty) {}
	public void setForQ (int qty) {}

	
	public static Item copy (Item item) {
		Item itemCopy = getInstance (item.getType(), item.getName(), item.getInitPrice());
		counter--;
		itemCopy.id=item.id;
		itemCopy.qty=item.qty;
		itemCopy.setDisc(item.getDisc());
		itemCopy.setInd (item.getInd ());
		itemCopy.setMindisc (item.getMindisc ());
		itemCopy.setBuyQ (item.getBuyQ ());
		itemCopy.setForQ (item.getForQ ());
		
		return itemCopy;
	}
	
	@Override
	public String toString () {
		String s="";
		s+=this.name+ "* "+this.getId() + " ** " + "EUR/pc " + this.getInitPrice()+"; ***-qty***" +this.getQty()+" pcs \n";
		s+="********** Net Total EUR: " + this.getNetTotal()+ " , incl. % EUR: " + this.getDisc() + "\n";
		return s;
	}
	
}