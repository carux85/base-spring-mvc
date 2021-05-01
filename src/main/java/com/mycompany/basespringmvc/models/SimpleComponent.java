package com.mycompany.basespringmvc.models;

import org.springframework.stereotype.Component;

@Component
public class SimpleComponent {
	
	private String name;
	
	public SimpleComponent() {
		name = "Default";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
