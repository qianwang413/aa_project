package com.aa.act.interview.org;

import java.util.*;

public abstract class Organization {

	private Position root;
	private int initialId;
	private Position curPosition;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();

	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Position hire(Name person, String title) {
		//create new employee
		Employee employee = new Employee(initialId++, person);
		Position position = root;

		getCurPosition(title, root);

		curPosition.setEmployee(Optional.of(employee));

		return curPosition;
	}

	public void getCurPosition(String title, Position position){
		if(position.getTitle() == title) {
			curPosition = position;
			return;
		};

		for(Position p : position.getDirectReports()){
			getCurPosition(title, p);
		}
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}

	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() +"\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
