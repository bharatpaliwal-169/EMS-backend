package com.ems.app.bean;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "email")
	private String email;
	
	
	public EmployeeBean() {
		
	}
	
	public EmployeeBean(String fullname, String email) {
		super();
		this.fullname = fullname;
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
