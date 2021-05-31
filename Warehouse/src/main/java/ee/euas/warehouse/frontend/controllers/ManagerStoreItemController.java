package ee.euas.warehouse.frontend.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ee.euas.warehouse.backend.items.Item;
import ee.euas.warehouse.backend.store.Store;
import ee.euas.warehouse.backend.store.Stores;
import ee.euas.warehouse.frontend.validation.ItemInput;
import ee.euas.warehouse.frontend.validation.ItemValidator;
import ee.euas.warehouse.frontend.validation.StoreInput;
import ee.euas.warehouse.frontend.validation.StoreValidator;


@Controller
public class ManagerStoreItemController extends ControllerCommonData implements WebMvcConfigurer {
	
	private ModelAndView storesEdit = new ModelAndView ("manager/storesEdit.html");
	private ModelAndView itemsEdit = new ModelAndView ("manager/itemsEdit.html");
	private ModelAndView itemsEditDiscounts = new ModelAndView ("manager/itemsEditDiscounts.html");
	
	
	private ItemValidator itemEdit;
	private Item item;


	
	// PAGE SETTINGS
	
	@ModelAttribute ("storenewch")
	public StoreValidator getNewStoreValidator () {
	return new StoreValidator ();}
	
	
	@ModelAttribute ("itemnewch")
	public ItemValidator getNewItemValidator () {
	return new ItemValidator ();}
	
	@ModelAttribute ("itemch")
	public ItemValidator displayItemValidator () {
	return itemEdit;}
	
	
	// PAGE MAPPINGS
	
	@RequestMapping ("manager_gostores")
	public ModelAndView managerGoStores () {
	return storesEdit;
}

	@RequestMapping ("manager_goitems")
	public ModelAndView managerGoItems () {
	updateItemsManager ();
	itemsEdit.addObject("items", itemsManager);
		return itemsEdit;
}
	
	@RequestMapping ("store_add")
	public ModelAndView addStore (@ModelAttribute ("storeinput") StoreInput st) {
	
		StoreValidator stV = new StoreValidator ();
		
		if (stV.createNew(st)!=null) stV = new StoreValidator ();
		updateStores ();
		storesEdit.addObject("storenewch", stV);
		return storesEdit;
	}

	@RequestMapping ("item_remove")
	public ModelAndView editRemoveItems (@RequestParam ("remove_index") int indexR) {
	
		if (itemsManager.size()>0) {

			Store.getItems().remove(indexR);
			
		}
		
		updateItemsManager ();
		
		itemsEdit.addObject("items", itemsManager);
		return itemsEdit;
		
}

	@RequestMapping ("item_addnew")
	public ModelAndView addItem (@ModelAttribute ("input") ItemInput it) {
	
		ItemValidator itV = new ItemValidator ();
		
		if (itV.createNew(it)!=null) itV = new ItemValidator ();
		updateItemsManager ();
		itemsEdit.addObject("itemnewch", itV);
		return itemsEdit;
	}
	
	@RequestMapping ("item_edit")
	public ModelAndView goEditItem (@RequestParam ("edit_index") int index) {
	
		item = Stores.getItems().get(index);
		itemEdit = new ItemValidator (item);
		
		
		itemsEditDiscounts.addObject("itemch", itemEdit);
		return itemsEditDiscounts;
		
}
	
	@RequestMapping ("item_amenddiscounts")
	public ModelAndView itemEdit (@ModelAttribute ("input") ItemInput it) {
		itemEdit.record(it);
		itemsEditDiscounts.addObject("itemch", itemEdit);
		return itemsEditDiscounts;
}
	
	}
