package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "Select u From Users u Order By u.fullName"),
		@NamedQuery(name = "Users.findByEmail", query = "Select u From Users u Where u.email=:email"),
		@NamedQuery(name = "Users.countAll", query = "Select Count(*) From Users u"),
		@NamedQuery(name = "Users.checkLogin", query = "Select u From Users u Where u.email=:email And password=:password") })
public class Users {
	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public Users() {
	}

	public Users(String email, String password, String fullName) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public Users(Integer userId, String email, String fullName, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
