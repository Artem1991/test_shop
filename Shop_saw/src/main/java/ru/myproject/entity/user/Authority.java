package ru.myproject.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="authorities")
public class Authority  implements Serializable{
	private static final long serialVersionUID = -7427619012799617697L;

	
	@Id
	@Column(name="id")
	private int id;
	
	
	@Column(name = "username")
	private String username;
	
	@Column(name="authority")
	private String authority;

	public Authority(){
		
	}
	
	public Authority(int id){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}

