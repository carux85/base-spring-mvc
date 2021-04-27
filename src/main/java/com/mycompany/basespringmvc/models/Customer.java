package com.mycompany.basespringmvc.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {

   @Id
   @GeneratedValue
   @Column(name = "id")
   private long id;
 
   @Column(name = "first_name")
   @Size(max = 20, min = 3, message = "{customer.first_name.invalid}")
   @NotEmpty(message="Please Enter your first name")
   private String firstName;

   @Column(name = "last_name")
   @Size(max = 20, min = 3, message = "{customer.last_name.invalid}")
   @NotEmpty(message="Please Enter your last name")
   private String lastName;
   
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "city_id") 
   private City city;
   
   public Customer() {}
   
   public Customer(String firstName, String lastName, City city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
   }
   
   public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s', city=%s]", id, firstName, lastName, city.toString());
	}
}
