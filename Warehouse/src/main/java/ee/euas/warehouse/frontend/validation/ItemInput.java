package ee.euas.warehouse.frontend.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class ItemInput {

private int	type;
private String	name;
private String	initPrice;
private String	disc;
private String	ind;
private String	mindisc;
private String	maxQ;
private String	buyQ;
private String	forQ;
	
}
