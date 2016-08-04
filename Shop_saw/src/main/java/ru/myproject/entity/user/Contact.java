package ru.myproject.entity.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact  implements Serializable{
	private static final long serialVersionUID = -7427619012799617697L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="message")
	private String message;
	
	public Contact(){
		
	}
	
	public Contact(String name){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

