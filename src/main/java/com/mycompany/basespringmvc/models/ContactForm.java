package com.mycompany.basespringmvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.mycompany.basespringmvc.validation.PhoneNumber;

public class ContactForm {

	@NotEmpty
	private String name;
	@NotEmpty
	private String surname;
	@NotEmpty(message = "{email.notempty}")
	@Email
	private String email;
	@PhoneNumber
	private String phone;
	@NotEmpty
	private String subject;
	@NotEmpty
	@Size(min=3, max=255)
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
