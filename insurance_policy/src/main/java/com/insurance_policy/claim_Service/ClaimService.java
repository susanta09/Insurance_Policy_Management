package com.insurance_policy.claim_Service;

import java.util.List;

import com.insurance_policy.dto.Claim_dto;

public interface ClaimService {
	public Claim_dto saveClaim(Claim_dto dto);
	public List<Claim_dto> getAllClaim();
	public Claim_dto getClaimById(Integer id);
	public Claim_dto updateClaimById(Integer id,Claim_dto dto);
	public String deleteClaimById(Integer id);

}
