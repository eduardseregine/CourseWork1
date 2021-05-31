package ee.euas.warehouse.frontend.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Validators {

	protected List<Boolean>  setFlags (List <String> inputs, List <Integer> patterns) {
		List <Boolean> flgs = new ArrayList<> ();
		if (patterns.size()>=inputs.size()) {
		
		flgs=IntStream.range(0,inputs.size())
				.mapToObj (i->checker (inputs.get (i),patterns.get (i)))
						.collect (Collectors.toList());}
		return flgs;
				
	}

	private boolean checker (String input, int pattern) {
		
		if (pattern == 1) {
			try {
				Double.parseDouble(input);
			} catch (NumberFormatException e) {
				return true;
			}  return false;
		}
		
		if (pattern == 3) {

		if (input.length() < 3) return true;
		
		for (int i = 0; i < 3; i++) {
			if (!Character.isLetter (input.charAt(i)))
				return true;}
		return false;
		}

		if (pattern == 7) {

			if (input.length() < 7) return true;
			
			try {
					Double.parseDouble(input);
				} catch (NumberFormatException e) {
					return true;
				}  return false;
		}
		return true;
	}
	
	protected static double toDouble (String string) {
		
	  double dbl=0;
		
	  if (string!=null) {
		try {
			dbl=Double.parseDouble(string);
		} catch (NumberFormatException e) {}  	}
		
		return Math.abs(dbl);
	}
	
	public static int toInt (String string) {
		int dbl = (int) toDouble (string);
		return dbl;
	}
	
	
}
