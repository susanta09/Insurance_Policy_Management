package com.insurance_policy.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class InsurancePolicy_dto {
	private Integer policy_No;
	@NotEmpty(message ="policy_Type can not be null")
	private String policy_Type;
	@NotNull(message = "Coverage_Amount can not be null")
	private Double Coverage_Amount;
	@NotNull(message = "premium can not be null")
	private Double premium;
	@NotNull
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",message = "valid date in valid formate dd/mm/yy")
	private String start_Time;
	@NotNull
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",message = "valid date in valid formate dd/mm/yy")
	private String end_Time;
	private List<Integer> claimsN;
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
	public List<Integer> getClaimsN() {
		return claimsN;
	}
	public void setClaimsN(List<Integer> claimsN) {
		this.claimsN = claimsN;
	}
	@Override
	public String toString() {
		return "InsurancePolicy_dto [policy_No=" + policy_No + ", policy_Type=" + policy_Type + ", Coverage_Amount="
				+ Coverage_Amount + ", premium=" + premium + ", start_Time=" + start_Time + ", end_Time=" + end_Time
				+ ", claimsN=" + claimsN + "]";
	}
	
	
	

}
