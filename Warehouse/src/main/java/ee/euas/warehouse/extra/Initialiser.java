package ee.euas.warehouse.extra;

import ee.euas.warehouse.backend.baskets.Basket;
import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.people.Cashier;
import ee.euas.warehouse.backend.people.Customer;
import ee.euas.warehouse.backend.people.Manager;
import ee.euas.warehouse.backend.store.Store;

public abstract class Initialiser {

	private static boolean isInitialised=false;
	
	
	public static void initialise () {
		if (!isInitialised) {
			isInitialised=true;
			startTime ();
			Store store= Store.getInstance(1, "Gringotts", "Diagon Alley, 64");
			Manager.managerInstance("Albus", "Dumbledore", "euas", "Hogwarts", "37200000", "1111", 123);
			Manager.managerInstance("Tom", "Voldemort", "euas", "Salazar", "101010101", "0000", 456);
			Cashier ch1=Cashier.cashierInstance (store, "Harry", "Potter", "euas", "Little Whinging", "49222222", "2222", 650);
			Cashier.cashierInstance (store, "Ron", "Weasley", "euas", "Chamber of Secrets", "74959999", "3333", 750);
			Cashier.cashierInstance (store, "Hermione", "Granger", "euas", "Gryffindor", "4101111", "4444", 850);
			Cashier.cashierInstance (store, "Malfoy", "Draco", "euas", "Slytherin", "98112233", "5555", 950);
			Cashier.cashierInstance (store, "Dudley", "Dursley", "euas", "Surrey, Privet Drive, 4", "850850850", "6666", 1050);
		
			Store.newItemToStores(1, "Grape", 100);
   Item it2=Store.newItemToStores(2, "Lemon", 100);
   Item it3=Store.newItemToStores(3, "Pineapple", 100);
   Item it4=Store.newItemToStores(4, "Redcurrant", 100);
   Item it5=Store.newItemToStores(5, "Watermelon", 100);
			it2.setDisc(10);
			it3.setDisc(20); it3.setInd(10);
			it4.setDisc(50); it4.setMindisc(5);
			it5.setBuyQ(5); it5.setForQ(3);
   
			Store.newItemToStores(4, "Lime", 2.5);
			Store.newItemToStores(1, "Orange", 3.0);
			Store.newItemToStores(3, "Apple", 1.5);
			Store.newItemToStores(3, "Kiwi", 6.2);
			Item it6=Store.newItemToStores(1, "Lucuma", 4.2);
			Store.newItemToStores(1, "Banana", 0.9);
			Item it7=Store.newItemToStores(5, "Guava", 8.0);
			Store.newItemToStores(1, "Mandarin", 4.0);
			Store.newItemToStores(2, "Mango", 7.0);
			Store.newItemToStores(2, "Peach", 5.5);
			Store.newItemToStores(2, "Pear", 3.3);
			
			Customer cust1=Customer.customerInstance(2, "Sirius", "Black", "euas", "London", "1234567");
			Customer cust2=Customer.customerInstance(3, "Severus", "Snape", "euas", "Hufflepuff", "56787656");
			Basket b1=Basket.getInstance(ch1, cust1);
			Basket b2=Basket.getInstance(ch1, cust2);
			b1.add(it6);
			b2.add(it7);
		}
	}
	
	private static void startTime () {
	Runnable task = () -> {DummyDate.run();};
	Thread thread = new Thread(task);
	thread.start();
	}
}
