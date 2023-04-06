package com.insurance_policy.policyService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance_policy.dto.InsurancePolicy_dto;

@Service
public interface InsurancePolicy_Service {
	public InsurancePolicy_dto savePolicy(InsurancePolicy_dto dto);
	public List<InsurancePolicy_dto> getAllPolicy();
	public InsurancePolicy_dto getPolicyById(Integer id);
	public InsurancePolicy_dto updatePolicyById(Integer id,InsurancePolicy_dto dto);
	public String deletePolicyById(Integer id);

}
