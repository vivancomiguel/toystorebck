package com.toystore.entity;

import javax.persistence.*;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.*;
import com.toystore.entity.abstract_class.*;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name="costumer")
public class Costumer extends CostumerAbstract {

	@NotNull
	@Column(name="password", length=PASSWORD_MAX_LENGTH)
//	@JsonIgnore
	private String password;
	private Boolean isActive;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
