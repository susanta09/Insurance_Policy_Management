package com.insurance_policy.claim_Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance_policy.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {

}
