package com.insurance_policy.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue
	private Long client_No;
	private String name;
	private String dob;
	private String phoneNo;
	@Embedded
	private Address address;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "policy_addTo_Client",
			joinColumns = {@JoinColumn(name="client_No")},
			inverseJoinColumns = {@JoinColumn(name="policy_No")}
			)
	private List<InsurancePolicy> policy;
	public Long getClient_No() {
		return client_No;
	}
	public void setClient_No(long i) {
		this.client_No = i;
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
	public List<InsurancePolicy> getPolicy() {
		return policy;
	}
	public void setPolicy(List<InsurancePolicy> policy) {
		this.policy = policy;
	}
	@Override
	public String toString() {
		return "Client [client_No=" + client_No + ", name=" + name + ", dob=" + dob + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", policy=" + policy + "]";
	}
	
	

}
