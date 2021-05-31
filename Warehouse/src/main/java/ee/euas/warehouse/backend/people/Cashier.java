package ee.euas.warehouse.backend.people;

import ee.euas.warehouse.extra.DummyDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.store.GetStoreItems;
import ee.euas.warehouse.backend.store.Store;
import ee.euas.warehouse.extra.Payable;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Cashier extends User implements Payable {

	final int NORMAL_HOURS_PER_DAY = 8;
	final double EUR_PER_OVERTIME_HOUR = 10;
	final int MAX_OVERTIME_PER_DAY = 8;
	final int WORKING_HOURS_PER_MONTH = 170;
	
	@Setter
	private String intPhone;
	@Setter
	private double baseSalary;
	@Setter
	private Store store;
	
	private GetStoreItems storeItems;
	
	private double normalHours;
	private double overtimeHours;
	private LocalDateTime firstLogin;
	private LocalDateTime lastLogout;
	private boolean isStartlogged;
	
	
	public Cashier(Store store, String name, String surname, String password, String address, String phoneNumber, String intPhone, double baseSalary) {
		super(name, surname, password, address, phoneNumber);
		this.lastLogout = DummyDate.getDate();
		this.firstLogin = DummyDate.getDate();
		this.normalHours=0;
		this.overtimeHours=0;
		this.baseSalary=baseSalary;
		this.intPhone=intPhone;
		this.store=store;
		this.storeItems=store;
		this.isStartlogged=true;
		
	}
	
	public Date getFirstLogin () {
		return Date.from(this.firstLogin.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date getLastLogout () {
		return Date.from(this.lastLogout.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public double getSalaryDue () {
		// for simplification baseSalary covers only normal hrs, where overtime is paid by EUR 10 per ce
	  return getSalaryNormal ()+getSalaryOver ();
	}
	
	public double getSalaryNormal () {
		return round (this.normalHours * this.baseSalary / this.WORKING_HOURS_PER_MONTH);
	}

	public double getSalaryOver ()	{
		return this.overtimeHours * this.EUR_PER_OVERTIME_HOUR;
		}

	public double getOverRate () {
		return EUR_PER_OVERTIME_HOUR;
	}
	
	
	@Override
	public void login() {
		
		if (isStartlogged) {
			this.firstLogin = DummyDate.getDate();
			this.lastLogout=this.firstLogin;
			isStartlogged=false;
		}
		
		if (!isWithinTheSameDay (this.firstLogin, DummyDate.getDate())) {
				calculateShiftHours();				
		this.firstLogin = DummyDate.getDate();
		this.lastLogout=this.firstLogin;
		}
		}

	
	@Override
	public void logout() {
		this.lastLogout = DummyDate.getDate(); 
	}
	
	public void startNewPeriod () {
		this.normalHours=0;
		this.overtimeHours=0;
		}
	
	private void calculateShiftHours () {
		
int shiftHours= hoursBetween (this.firstLogin, this.lastLogout);	

if (shiftHours<=NORMAL_HOURS_PER_DAY) this.normalHours+=shiftHours;
else {this.overtimeHours += Math.min(shiftHours-NORMAL_HOURS_PER_DAY, MAX_OVERTIME_PER_DAY);
this.normalHours+=NORMAL_HOURS_PER_DAY;
}
	}
	
	
	private boolean isWithinTheSameDay (LocalDateTime firstDate, LocalDateTime lastDate) {
		return firstDate.getDayOfMonth()==lastDate.getDayOfMonth();
	}
	
	private int hoursBetween (LocalDateTime firstDate, LocalDateTime lastDate) {
		return 	(int) firstDate.until(lastDate, ChronoUnit.HOURS);
		}
	
	@Override
	public String toString () {
		String s="Cashier: [InternalPhoneNumber " + this.intPhone + ", baseSalary " + this.baseSalary+"\n";
		s+=super.toString()+"\n";
		s+="firstLogin: " + this.firstLogin ;
		return s;
	}

	@Override
	public void pay() {
		this.normalHours=0;
		this.overtimeHours=0;
		
	}
	
	public int getStoreId () {
		return this.store.getId();
	}
	
	public String getStoreAddress () {
		return this.store.getAddress();
	}
	
	public String getStoreName () {
		return this.store.getName();
	}
	
	public List <Item> getItems () {
		return storeItems.getItemsCashier();
	}
	
	protected double round (double amount) {
		int temp = (int) Math.round(amount*100);
		return temp/100;
	}
}
