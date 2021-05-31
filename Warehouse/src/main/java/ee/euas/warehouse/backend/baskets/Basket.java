package ee.euas.warehouse.backend.baskets;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import ee.euas.warehouse.extra.DummyDate;
import ee.euas.warehouse.extra.Payable;
import lombok.Getter;

@Getter
public class Basket  extends StoreBasket implements Payable{

	private static int counter = 0;

	final double VAT_RATE=0.2;
	private int id;
	protected LocalDateTime dOfP;	
	protected boolean isPaid;
	private boolean isPromotionApplied;

	private List <Item> bItems;
	

	protected Basket(Cashier cashier, Customer customer) {
		this.cashier = cashier;
		this.customer = customer;
	counter++;
	this.id=counter;
	this.dOfP=DummyDate.getDate(); 
	bItems = new ArrayList <> (); 
	this.isPaid = false;
	this.isPromotionApplied=false;
	}

	public Date getDateOfPurchase () {
		return Date.from(dOfP.atZone(ZoneId.systemDefault()).toInstant());
		}
	
	public double getNetAmount () {
		return Math.rint((bItems
				.stream()
				.map (b->b.getNetTotal())
				.mapToDouble (x->x).sum())*100)/100;
	}
	
	public double getDiscounts () {
		return 
				Math.rint(
				bItems
				.stream()
				.map (b->b.getDiscounts())
				.mapToDouble (x->x).sum()*100)/100;
	}
	
	public double getVat () {
		return Math.rint(VAT_RATE*this.getNetAmount()*100)/100;
	}
	
	public double getTotalAmount () {
		return Math.rint((this.getNetAmount()+this.getVat())*100)/100;
	}
	
	public int getPoints () {
		return 0;
	}
	

	
	
	public void add (Item item) {
		if (!this.isPaid && !this.isPromotionApplied) {
		Item addedIt =Item.copy(item);
			bItems.add(addedIt);
			addedIt.setQty(1);
		}
	}
	
	private void remove () {
		if (!this.isPaid) this.bItems=bItems
				.stream()
				.filter(x->x.getQty()>0)
				.collect(Collectors.toList());
	}
	
	public void increment (int index) {
		if (!this.isPaid && !this.isPromotionApplied) {
		if (index<bItems.size()) {
			if (bItems.get(index).getId()!=0) {
			bItems.get(index).setQty(bItems.get(index).getQty()+1);}
		}}
	}
	
	public void decrement (int index) {
		if (!this.isPaid && !this.isPromotionApplied) {
		if (index<bItems.size()) {
			if (bItems.get(index).getId()!=0) {
			bItems.get(index).setQty(bItems.get(index).getQty()-1);
			if (bItems.get(index).getQty()==0) remove ();}
		}}
	}
	
	public void applyPromotion () {
		boolean isExist = bItems
				.stream()
				.map (x->x.getId())
				.anyMatch(x->x==0);
	
		if (!isExist && !this.isPaid) {
		bItems.add(Item.promotionInstance(getNetAmount()));
		this.isPromotionApplied=true;
		}
	}
	
	@Override
	public void pay() {
		if (!this.isPaid) {
			this.isPaid=true;
			this.dOfP=DummyDate.getDate();
			}
	}

	@Override
	int calculateBonus() {
		return 0;
	}

	@Override
	public String printInfo() {
		return this.toString();
		
	}
	
	@Override
	public String toString () {
		String s="Basket " + this.id+ ": Net amount: EUR"+this.getNetAmount()+", VAT: EUR" + this.getVat()+", Total: EUR" +this.getTotalAmount()
		+ "<br>"+st()+"<br>";
				s+=" incl.- Discount on Items: EUR " + this.getDiscounts() +"<br>" +" - Bonus for future purchases, EUR: " + this.calculateBonus() +"<br><br>"; 
	
		if (this.isPaid) s+="-- Status: Paid <br>"; else s+="-- Status: Due <br>"; 
		s+=st()+"\n"+ "<br>"+st()+ "<br>";
		s+="Basket Items: <br>";
		s+=bItems
				.stream()
				.map(x->x.printInfo()).reduce("<br>", (res, next) -> res + next+ "; <br>"+ "<br>");
				
		return s;
	}
	
	private String st () {
		return "-------**********************************************************----- <br>";
	}
}
