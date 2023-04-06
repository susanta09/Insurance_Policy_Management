package com.insurance_policy.dto;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Client_dto {
	private Long client_Id;
	@NotNull(message = "name can not be null")
	@Pattern(regexp = "^[A-Z][a-z]+\\W[A-Z][a-z]+$",
			  message ="First letter of String Must be capital.Take a space between two string")
	private String name;
	@NotNull(message = "date of birth can not be null")
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",
			  message = "date formate dd/mm/yy")
	private String dob;
	@NotNull(message = "Phone number can not be null")
	@Pattern(regexp = "^(0|91)?[7-9][0-9]{9}$",
			  message ="Enter the valid phone number.first 0 or 91 optional but actual number must be 10 digits" )
	private String phoneNo;
	
	@NotNull(message = "Address can not be null")
	@Valid
	private Address address;
	private List<Integer> policy_No;
	public Long getClient_Id() {
		return client_Id;
	}
	public void setClient_Id(long i) {
		this.client_Id = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Integer> getPolicy_No() {
		return policy_No;
	}
	public void setPolicy_No(List<Integer> policy_No) {
		this.policy_No = policy_No;
	}
	
	

}
