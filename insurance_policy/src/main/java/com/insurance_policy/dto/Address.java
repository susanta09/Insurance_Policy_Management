package com.insurance_policy.dto;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Embeddable
public class Address {
	@NotNull(message = "City not be null")
	private String city;
	@NotNull(message = "state not be null")
	private String state;
	@NotNull(message = "country not be null")
	private String country;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
