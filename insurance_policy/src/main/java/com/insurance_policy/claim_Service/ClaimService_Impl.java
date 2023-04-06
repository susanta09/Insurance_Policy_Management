package com.insurance_policy.claim_Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance_policy.API_ExpectionHandler.ResourceNotFoundException;
import com.insurance_policy.claim_Repository.ClaimRepository;
import com.insurance_policy.dto.Claim_dto;
import com.insurance_policy.model.Claim;
@Service
public class ClaimService_Impl implements ClaimService {
	@Autowired
	private ClaimRepository claimRepository;
	public Claim bindFrom_dto(Claim_dto dto)
	{
		Claim claim=new Claim();
		claim.setClaim_No(dto.getClaim_No());
		claim.setDescription(dto.getDescription());
		claim.setClaim_Date(dto.getClaim_Date());
		claim.setClaim_Status(dto.getClaim_Status());
		return claim;
	}
	public Claim_dto bindFrom_claim(Claim claim)
	{
		Claim_dto dto=new Claim_dto();
		dto.setClaim_No(claim.getClaim_No());
		dto.setDescription(claim.getDescription());
		dto.setClaim_Date(claim.getClaim_Date());
		dto.setClaim_Status(claim.getClaim_Status());
		return dto;
	}
	@Override
	public Claim_dto saveClaim(Claim_dto dto) {
		Claim c=bindFrom_dto(dto);
		c=claimRepository.save(c);
		System.out.println(c);
		return bindFrom_claim(c);
	}

	@Override
	public List<Claim_dto> getAllClaim() {
		List<Claim_dto> dtoli=new ArrayList<Claim_dto>();
		List<Claim> li=claimRepository.findAll();
		for (Claim claim : li) {
			dtoli.add(bindFrom_claim(claim));
		}
		return dtoli;
	}

	@Override
	public Claim_dto getClaimById(Integer id) {
	      Claim claim=claimRepository.findById(id)
	    		  .orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
	     return bindFrom_claim(claim); 
	}

	@Override
	public Claim_dto updateClaimById(Integer id, Claim_dto dto) {
		 Claim claim=claimRepository.findById(id)
	    		  .orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
		   claim=bindFrom_dto(dto);
		   claim.setClaim_No(id);
		   claimRepository.save(claim);
		   return bindFrom_claim(claim);
		
		
	}
	@Override
	public String deleteClaimById(Integer id) {
		 Claim claim=claimRepository.findById(id)
	    		  .orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
			claimRepository.deleteById(id);
			return "Specified resource successfully deleted.";
	}

}
