package ee.euas.warehouse.backend.store;

import java.util.List;
import java.util.stream.Collectors;

import ee.euas.warehouse.backend.items.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PremiumStore extends Store implements GetStoreItems {

	@Setter
	private int premium; // percent
	private boolean premiumAdded;



	protected PremiumStore(String name, String address) {
		super(name, address);
		premiumAdded=false;
	}

		
	@Override
	public List<Item> getItemsCashier() {
		
		
		return Stores.getItems()
				.stream()
				.map(x -> Item.copy(x))
				.peek (y->y.setInitPrice(y.getInitPrice()*(100+premium)/100))
			    .collect(Collectors.toList());

	}
		
	

}
