package ee.euas.warehouse.frontend.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ee.euas.warehouse.backend.people.Manager;
import ee.euas.warehouse.backend.people.User;
import ee.euas.warehouse.frontend.validation.CashierInput;
import ee.euas.warehouse.frontend.validation.CashierValidator;
import ee.euas.warehouse.frontend.validation.ManagerInput;
import ee.euas.warehouse.frontend.validation.ManagerValidator;



@Controller
public class ManagerPeopleController extends ControllerCommonData implements WebMvcConfigurer {

	private ModelAndView managersEdit = new ModelAndView ("manager/managersEdit.html");
	private ModelAndView cashiersEdit = new ModelAndView ("manager/cashiersEdit.html");
	
	private List <Manager> managersLocal;
	
	private ManagerValidator managerEdit;
	
	
	// PAGE SETTINGS
	
	@ModelAttribute ("selfch")
	public ManagerValidator getManagerEditSelf () {
		updateManagers ();
		getManager ();
		managerEdit=new ManagerValidator (manager);
	  return managerEdit;}

	@ModelAttribute ("managernewch")
	public ManagerValidator inputNewManager () {
		ManagerValidator managerNew = new ManagerValidator ();	
		return managerNew;
	}
	
	@ModelAttribute ("managerchs")
	public List <Manager> updateLocalManagers () {
		updateManagers ();
		managersLocal=allmanagers.stream()
			.filter(x->x.getId()!=getUserId())
            .collect(Collectors.toList());
		return managersLocal;}	
	
	@ModelAttribute ("cashiernewch")
	public CashierValidator getNewCashierValidator () {
	return new CashierValidator ();	
	}
	
	
	// PAGE MAPPINGS
	
	@RequestMapping ("manager_gomanagers")
	public ModelAndView goManagers () {
		
		getManagerEditSelf ();
		managersEdit.addObject("selfch", managerEdit);
		
		updateLocalManagers ();
		managersEdit.addObject("managerchs",managersLocal);
		
		return managersEdit;
}

	@RequestMapping ("manager_gocashiers")
	public ModelAndView goCashiers () {
	return cashiersEdit;
}

	// BUTTONS MAPPING MANAGER EDIT
	
	@RequestMapping ("self_edit")
	public ModelAndView selfEdit (@ModelAttribute ("selfinput") ManagerInput mng) {
		managerEdit.record(mng);
		managersEdit.addObject("selfch", managerEdit);
		return managersEdit;
}

	@RequestMapping ("manager_add")
	public ModelAndView addManager (@ModelAttribute ("newmanagerinput") ManagerInput mng) {
		
		ManagerValidator mgv =new ManagerValidator ();
		
		if (mgv.createNew(mng)!=null) 
			mgv=new ManagerValidator ();
		
		managersEdit.addObject("managernewch", mgv); 	
		updateLocalManagers ();
		managersEdit.addObject("managerchs",managersLocal);
		
		return managersEdit;	
}
	
	@RequestMapping ("manager_remove")
	public ModelAndView removeManager (@RequestParam ("remove_index") int index) {
	
		int id = managersLocal.get(index).getId();
		int indexToRemove = IntStream.range(0, User.getManagers()
				.size())
				.filter(x->User.getManagers().get(x).getId()==id)
				.findFirst()
				.orElse(-1);
		if (indexToRemove>=0) User.getManagers().remove(indexToRemove);
		
		updateLocalManagers ();
		managersEdit.addObject("managerchs",managersLocal);
		
		return managersEdit;
	}
	
	
	@RequestMapping ("cashier_add")
	public ModelAndView addCashier (@ModelAttribute ("input") CashierInput ch) {
	
		CashierValidator chV = new CashierValidator ();
		
		if (chV.createNew(ch)!=null) chV = new CashierValidator ();
		updateItemsManager ();
		cashiersEdit.addObject("cashiernewch", chV);
		return cashiersEdit;
	}
	
	@RequestMapping ("cashier_remove")
	public ModelAndView removeCashiers (@RequestParam ("remove_index") int index) {
	
		if (cashiers.size()>0) {

			User.getCashiers().remove(index);
		}
		
		updateCashiers ();
		
		cashiersEdit.addObject("cashiers", cashiers);
		return cashiersEdit;
		
}

}
