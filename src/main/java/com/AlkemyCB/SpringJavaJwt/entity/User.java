package com.AlkemyCB.SpringJavaJwt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;

@Entity
@Table
@Data
public class User {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idUser;
	
	
	@Column
	private String user;
	
	@Column
	private String password;
	
	@Column
	@Email
	private String email;
	
	
	@ManyToOne
	@JoinColumn(name ="rol_id")
	private Rol rolUser;
	
	
	public User() {}

	public User (String user,String password, String email ) {
		this.user=user;
		this.password=password;
		this.email=email;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRolUser() {
		return rolUser;
	}

	public void setRolUser(Rol rolUser) {
		this.rolUser = rolUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
