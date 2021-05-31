package ee.euas.warehouse.backend.people;

public class Password {

	private String password;
	
	public static Password setPassword (String password) {
		Password pas=new Password (password);
		return pas;
	}
	
	private Password (String password) {
		this.password=password;
			}
	
	public boolean isPasswordCorrect (String password) {
		return this.password.equals(password);
	}
}
