package ee.euas.warehouse.backend.people;

import java.util.ArrayList;
import java.util.List;

import ee.euas.warehouse.backend.store.Store;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public abstract class User {

	private static int counter = 0;
	private int id;
	@Setter
	private String name;
	@Setter
	private String surname;
	@Setter
	private String userName;
	private Password password;
	@Setter
	private String address;
	@Setter
	private String phoneNumber;
	
	
	@Getter
	private static List <Customer> customers=new ArrayList <> ();
	
	@Getter
	@Setter
	private static List <Cashier> cashiers=new ArrayList <> ();
	
	@Getter
	private static List <Manager> managers;
	
	
	protected User(String name, String surname, String password, String address, String phoneNumber) {
	    if (counter==0) managers=new ArrayList <> ();
		counter++;
	    this.id=counter;
		this.name = name;
		this.surname = surname;
		this.userName = surname.toLowerCase();
		this.password = Password.setPassword(password) ;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	abstract void login ();
	abstract void logout ();
	
	public static User getLoggedUser (String userName, String password) {
		
		User user=null;
		for (User u: User.getUsers()) {
			if (u.getUserName().equals(userName)) 
				if (u.getPassword().isPasswordCorrect(password)) user=u; }
		if (user!=null) user.login();
		
		return user;
	}

	public static Customer customerInstance(int type, String name, String surname, String password, String address,
			String phoneNumber) {

		Customer newCustomer=null;
		
		if (type == 2)
			newCustomer=new LoyalCustomer(name, surname, password, address, phoneNumber);
		if (type == 3)
			newCustomer = new EmployeeCustomer(name, surname, password, address, phoneNumber);

		if (type !=2 && type!=3) newCustomer=new Customer(name, surname, password, address, phoneNumber);
		
		customers.add(newCustomer);
		return newCustomer;
		
	}

	public static Manager managerInstance (String name, String surname, String password, String address, String phoneNumber, String intPhone, int pin) {
		
		Manager newManager = new Manager (name, surname, password, address, phoneNumber, intPhone, pin);
	    managers.add(newManager);
		return newManager;
	}
	
    public static Cashier cashierInstance (Store store, String name, String surname, String password, String address, String phoneNumber, String intPhone, double baseSalary) {
		
		Cashier newcashier = new Cashier (store, name, surname, password, address, phoneNumber, intPhone, baseSalary);
	    cashiers.add(newcashier);
		return newcashier;
	}
	
    public static boolean isPinExists (int pin) {
    	
    	return managers.stream().map(x->x.getPin()).anyMatch(x->x.equals(pin));
    	
    }
    
    public static List <User> getUsers () {
    	List <User> users = new ArrayList <> ();
    	users.addAll(managers);
    	users.addAll(cashiers);
    	users.addAll(customers);
    	return users;
    }
    
  
}
