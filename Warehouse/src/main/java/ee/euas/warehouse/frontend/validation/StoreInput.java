package ee.euas.warehouse.frontend.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StoreInput {

	private int type;
	private String name;
	private String address;
	private String premium;
	
}
