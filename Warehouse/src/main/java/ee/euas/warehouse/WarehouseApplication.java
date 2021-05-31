package ee.euas.warehouse;


import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class WarehouseApplication {
	
	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
    }

		
	public static void main(String[] args) {
	SpringApplication.run(WarehouseApplication.class, args);
	TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
		
			
		System.out.println(
				
				"Tere!" + "\n\n"
				+ ""
				+ "  This is the final assignment at Java OOP for SE-2020" + "\n\n"
				+ ""
				+ "PLEASE ENSURE: " + "\n\n"
				+ ""
				+ " - to import as Gradle project into IDE and download dependencies;"+ "\n"
				+ " - to start this application and wait until you see this text in console;"+ "\n"
				+ " - to go to 'http://localhost:8080/' with your browser." +"\n"
				+ "  ============PASSWORD INFORMATION:========================"  +"\n"
				+ "**********  PIN - '123' or '456' *************"+"\n"
				+ "************PASSWORD - ALWAYS     'euas'      ********"+"\n"
				+ "  ==================================================="  +"\n"
				+ "MANAGER LOGINS: 'dumbledore' or 'voldemort' "+"\n"
				+ "CASHIER LOGINS: 'potter', 'weasley', 'granger', 'draco' or 'dursley' "+"\n"
				+ "CUSTOMER LOGINS: 'black' or 'snape' "+"\n"
				+ "  ==================================================="  +"\n\n"
				
				+ "The final project utilises @SpringBoot, @Thymeleaf and @Lombok libraries."+ "\n"
				+ ""
				+ "Thank you!"
				+ "");
		
				
}
}
