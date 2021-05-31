package ee.euas.warehouse.backend.store;

import java.util.ArrayList;
import java.util.List;

import ee.euas.warehouse.backend.items.Item;
import lombok.Getter;
import lombok.Setter;

public abstract class Stores {

	@Getter
	@Setter
	protected static List <Item> items=new ArrayList <> ();
	
	@Getter
	protected static List <Store> allStores=new ArrayList <> ();
	
	public static Store getInstance (int type, String name, String address) {
		
		Store newStore;
		if (type==2) newStore=new PremiumStore (name, address);
		else newStore=new Store (name, address);
		allStores.add(newStore);
		
		return newStore;
}
	
	public int getType () {
		if (this instanceof PremiumStore) return 2;
		return 1;
	} 
	
	public static Store getStoreById (int id) {
		return allStores.stream().filter(x->x.getId()==id).findFirst().orElse(null);
	
	}
	
	
	public static Item newItemToStores (int type, String name, double initPrice) {
		Item it = Item.getInstance(type, name, initPrice);
		items.add(it);
		return it;
	}
	

	

}

