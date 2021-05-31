package ee.euas.warehouse.backend.people;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Manager extends User {

	@Setter
	private String intPhone;
	private int pin;

	protected Manager(String name, String surname, String password, String address, String phoneNumber, String intPhone, int pin) {
		super(name, surname, password, address, phoneNumber);
		this.pin=pin;
		this.intPhone=intPhone;
	}

	public void setPin (int pin) {
		if (this.pin>0) this.pin=pin;
	}
	
	
	@Override
	void login() {
			
	}

	@Override
	void logout() {
			
	}
	
	@Override
	public String toString () {
		String s="Manager: [InternalPhoneNumber " + this.intPhone + ", pin " + this.pin+"\n";
		s+=super.toString();
		return s;
	}
}
