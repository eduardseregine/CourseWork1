package ee.euas.warehouse.backend.store;

import java.util.List;

import ee.euas.warehouse.backend.items.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Store extends Stores implements GetStoreItems{

	private static int count=0;
	private int id;
	@Setter
	private String name;
	@Setter
	private String address;
	
	
	protected Store(String name, String address) {
		count++;
		this.id=count;
		
		while (checkName (name)) name+="_abc";
		this.name = name;
		this.address = address;
		}
	
	@Override
	public List <Item> getItemsCashier () {
		return Stores.getItems ();	
	}
	
	public int getPremium () {
		return 0;
	}
	
	public void setPremium (int premium) {}
	
	
	private boolean checkName (String name) {
		boolean isExist=false;
		//if (allStores!=null)
		for (Store st: allStores) {if (st.getName().equalsIgnoreCase(name)) isExist=true;}
		return isExist;
	}
	

}
