package ee.euas.warehouse.frontend.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class CashierInput {

private int storeId;

private String surname;
private String name;
private String address;
private String phoneNumber;
private String userName;
private String password;
private String intPhone;

private String baseSalary;

	
}
