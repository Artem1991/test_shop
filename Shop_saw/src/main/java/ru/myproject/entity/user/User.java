package ru.myproject.entity.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User  implements Serializable{
	private static final long serialVersionUID = -7427619012799617697L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	
	///@GenericGenerator(name="kaugen" , strategy="increment")
	//@GeneratedValue(generator="kaugen")
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role;
	
	
	public User(){
		
	}
	
	public User(String username){
		
	}

	public String getUsername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}

