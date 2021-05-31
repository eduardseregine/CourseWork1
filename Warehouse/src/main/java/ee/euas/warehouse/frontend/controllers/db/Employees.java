package ee.euas.warehouse.frontend.controllers.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
@Entity
public class Employees {

	@Id
	private int id;
	@Column
	private String name;
	@Column
	private double salary;
	
	public Employees () {
		
	}

	public Employees(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	
	
}
