package com.insurance_policy.policyRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance_policy.model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

}
