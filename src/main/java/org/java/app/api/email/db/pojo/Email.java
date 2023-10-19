package org.java.app.api.email.db.pojo;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false) 
	@NotNull
	private String email;
	
	@Column(nullable = false)
	@NotNull
	@Length(min = 10)
	private String text;
	
	
	
	
	public Email() {}
	
	public Email(String email, String text) {
		setEmail(email);
		setText(text);
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "[ " + getId() + " ]:\n"
				+ getEmail() + "\n" 
				+ getText();
				
	}
}
