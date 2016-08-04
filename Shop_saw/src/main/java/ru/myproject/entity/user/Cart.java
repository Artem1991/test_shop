package ru.myproject.entity.user;



import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart  implements Serializable{
	private static final long serialVersionUID = -7427619012799617697L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	private int id_user;
	
	@Column(name="id_saw")
	private String id_saw;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date")
	private String date;
	
	@Column(name="id")
	private int id;
	
	public Cart(){
		
	}
	
	public Cart(String id_user){
		
	}

	public int getUserID() {
		return id_user;
	}

	public void setUserID(int id_user) {
		this.id_user = id_user;
	}
	
	public String getSawID() {
		return id_saw;
	}

	public void setSawID(String id_saw) {
		this.id_saw = id_saw;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	
	
}
