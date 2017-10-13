package com.bitwiseglobal.resumemgmt.entityvo;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_key", nullable=false, length=20)
	BigInteger userKey;
	
	@Column(name="user_id", nullable=false, length=50)
	String userId;

	@Column(name="password", nullable=false, length=10)
	String password;
	
	@Column(name="first_name", nullable=false, length=200)
	String firstName;
	
	@Column(name="middle_name", nullable=false, length=200)
	String middleName;
	
	@Column(name="last_name", nullable=false, length=200)
	String lastName;
		
	public BigInteger getUserKey() {
		return userKey;
	}
	public void setUserKey(BigInteger userKey) {
		this.userKey = userKey;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
