package com.mycompany.basespringmvc.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="articles")
public class Article {
	
	@Id
	private String id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	public Article() {}
	
	public Article(String id, String name,  Brand brand) {
		this.id = id;
		this.name = name;
		this.brand = brand;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
