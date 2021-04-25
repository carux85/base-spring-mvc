package com.mycompany.basespringmvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_customers")
public class CustomerHibernate {

   @Id
   @GeneratedValue
   @Column(name = "id")
   private Long id;
 
   @Column(name = "first_name")
   @Size(max = 20, min = 3, message = "{customer.first_name.invalid}")
   @NotEmpty(message="Please Enter your first name")
   private String firstName;

   @Column(name = "last_name")
   @Size(max = 20, min = 3, message = "{customer.last_name.invalid}")
   @NotEmpty(message="Please Enter your last name")
   private String lastName;
   
   public CustomerHibernate() {}
   
   public CustomerHibernate(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
}
