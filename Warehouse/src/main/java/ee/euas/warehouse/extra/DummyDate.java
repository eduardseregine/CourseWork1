package ee.euas.warehouse.extra;

import java.time.LocalDateTime;


public abstract class DummyDate {

	
	private static LocalDateTime date=LocalDateTime.now();


	public static LocalDateTime getDate () {
		return date;
		}
	
	

	// Run the Dummy Date
	
	public static void run () {
	
		
LocalDateTime today = LocalDateTime.now();

		
		LocalDateTime dateToReturn=today;
		dateToReturn=dateToReturn.withSecond(0);
		dateToReturn=dateToReturn.minusMinutes(dateToReturn.getMinute()%10);
		
		LocalDateTime counter=today.plusSeconds(1);
		
		while (true) {
			today = LocalDateTime.now();
			if (today.compareTo(counter)>0) {
				dateToReturn = dateToReturn.plusMinutes(15);
				counter=counter.plusSeconds(1);
				date=dateToReturn;
				if (dateToReturn.getHour()<8) dateToReturn=dateToReturn.plusHours(8-dateToReturn.getHour());	
			}
			
			
		
		}}
	}
			
		
//		System.out.println(f.until(dateToReturn, ChronoUnit.HOURS));
//		
//		d=Date.from(dateToReturn.atZone(ZoneId.systemDefault()).toInstant());
		
//	}

		
		
		
// OLD VERSION
		
//	    Calendar today= Calendar.getInstance();
//	    LocalDateTime todayL=LocalDateTime.now();
//	    LocalDateTime dateToReturnL=todayL;
//	    
//		Calendar dateToReturn = Calendar.getInstance();
//		dateToReturn.set(Calendar.SECOND, 0);
//
//		int min = dateToReturn.get(Calendar.MINUTE);
////		int min=todayL.getMinute();
//		
//		min = Math.round(min/5)*5;
//		dateToReturn.set(Calendar.MINUTE, min);
//		
//		LocalDateTime counterL=todayL.plusSeconds(1);
//		
//		todayL = LocalDateTime.now();
//		if (todayL.compareTo(counterL)>0) {	
//			dateToReturnL = dateToReturnL.plusMinutes(5);}
//		
//		
//		todayL.plusMinutes(min);
//	todayL.compareTo(todayL);
//	
//	
//		Calendar counter = today;
//		while (true) {
//			today= Calendar.getInstance();
//			if (dateToReturn.get(Calendar.HOUR)<8) {
//				dateToReturn.set(Calendar.HOUR, 8);
//				}
//			if (counter.compareTo(today)<0) {
//				counter.add(Calendar.SECOND, 1); 
//				dateToReturn.add(Calendar.MINUTE, 5);
//			date=dateToReturn; }
//	}
		
//}
	
	

