package com.toystore.entity.abstract_class;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class CostumerAbstract {
	public static final int FIRST_NAME_MAX_LENGTH = 150;
	public static final int LASTNAME_MAX_LENGTH = 150;
	public static final int AVATAR_MAX_LENGTH = 150;
	public static final int EMAIL_MAX_LENGTH = 150;
	public static final int PASSWORD_MAX_LENGTH = 150;
	public static final int ADDRESS_MAX_LENGTH = 200;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="costumer_id")
	protected Long costumerId;
	@Column(name="first_name", length=FIRST_NAME_MAX_LENGTH)
	protected String firstName;
	@Column(name="last_name", length=LASTNAME_MAX_LENGTH)
	protected String lastName;
	@Column(name="avatar", length=AVATAR_MAX_LENGTH)
	protected String avatar;
	@NotNull 
	@Column(name="email", updatable=false, length=EMAIL_MAX_LENGTH)	
	protected String email;
	@Column(name="address", length=ADDRESS_MAX_LENGTH)
	protected String address;
	
	public Long getCostumerId() {
		return costumerId;
	}
	public void setCostumerId(Long costumerId) {
		this.costumerId = costumerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
