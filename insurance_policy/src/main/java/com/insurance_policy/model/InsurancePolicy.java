package com.insurance_policy.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
public class InsurancePolicy {
	@Id
	@GeneratedValue
	private Integer policy_No;
	private String policy_Type;
	private Double Coverage_Amount;
	private Double premium;
	private String start_Time;
	private String end_Time;
	
	@OneToMany(targetEntity = Claim.class,cascade = CascadeType.REMOVE)
	@JoinColumn(name = "policy_No" )
	private List<Claim> claims;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "policy_addTo_Client",
			joinColumns = {@JoinColumn(name="policy_No")},
			inverseJoinColumns = {@JoinColumn(name="client_No")}
			)
	private List<Client> clients;
	public Integer getPolicy_No() {
		return policy_No;
	}
	public void setPolicy_No(Integer policy_No) {
		this.policy_No = policy_No;
	}
	public String getPolicy_Type() {
		return policy_Type;
	}
	public void setPolicy_Type(String policy_Type) {
		this.policy_Type = policy_Type;
	}
	public Double getCoverage_Amount() {
		return Coverage_Amount;
	}
	public void setCoverage_Amount(Double coverage_Amount) {
		Coverage_Amount = coverage_Amount;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public String getStart_Time() {
		return start_Time;
	}
	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}
	public String getEnd_Time() {
		return end_Time;
	}
	public void setEnd_Time(String end_Time) {
		this.end_Time = end_Time;
	}
	public List<Claim> getClaims() {
		return claims;
	}
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	@Override
	public String toString() {
		return "InsurancePolicy [policy_No=" + policy_No + ", policy_Type=" + policy_Type + ", Coverage_Amount="
				+ Coverage_Amount + ", premium=" + premium + ", start_Time=" + start_Time + ", end_Time=" + end_Time
				+ ", claims=" + claims + ", clients=" + clients + "]";
	}
	
	
	
}
