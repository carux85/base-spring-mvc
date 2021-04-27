package com.mycompany.basespringmvc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City implements Serializable{

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue
   @Column(name = "id")
   private long id;
   
   @Column(name = "name")
   private String name;
   
   @Column(name = "country")
   private String country;
   
   public City() {}
   
   public City(String name, String country) {
	   this.name = name;
	   this.country = country;
   }
   
   public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return String.format("City[id=%d, name='%s', country='%s']", id, name, country);
	}
}
