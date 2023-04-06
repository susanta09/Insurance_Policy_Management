package com.insurance_policy.policyService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance_policy.API_ExpectionHandler.ResourceNotFoundException;
import com.insurance_policy.claim_Repository.ClaimRepository;
import com.insurance_policy.dto.InsurancePolicy_dto;
import com.insurance_policy.model.Claim;
import com.insurance_policy.model.InsurancePolicy;
import com.insurance_policy.policyRepository.InsurancePolicyRepository;
@Service
public class InsurancePolicy_Service_imp implements InsurancePolicy_Service{
	@Autowired
	private InsurancePolicyRepository policyRepository;
	@Autowired
	private ClaimRepository claimRepository;
	
	public InsurancePolicy bindFrom_dto(InsurancePolicy_dto dto)
	{
		InsurancePolicy policy=new InsurancePolicy();
		policy.setPolicy_Type(dto.getPolicy_Type());
		policy.setCoverage_Amount(dto.getCoverage_Amount());
		policy.setPremium(dto.getPremium());
		policy.setStart_Time(dto.getStart_Time());
		policy.setEnd_Time(dto.getEnd_Time());
		List<Claim> li=new ArrayList<Claim>();
		System.out.println(li);
		List<Integer>cList=dto.getClaimsN();
		System.out.println(cList);
		if(cList!=null)
		{
			System.out.println("tt"+cList);
			for (Integer integer : cList) {
				Claim claim=claimRepository.findById(integer)
					.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+integer));
					li.add(claim);
				}
		}else {
			li=null;
		}
		policy.setClaims(li);
		return policy;
	}
	public InsurancePolicy_dto bindFrom_policy(InsurancePolicy policy)
	{
		InsurancePolicy_dto dto=new InsurancePolicy_dto();
		dto.setPolicy_No(policy.getPolicy_No());
		dto.setPolicy_Type(policy.getPolicy_Type());
		dto.setCoverage_Amount(policy.getCoverage_Amount());
		dto.setPremium(policy.getPremium());
		dto.setStart_Time(policy.getStart_Time());
		dto.setEnd_Time(policy.getEnd_Time());
		List<Integer>claimli=new ArrayList<Integer>();
		List<Claim>li=policy.getClaims();
		if(li!=null)
		{
			for (Claim claim : li) {
				claimli.add(claim.getClaim_No());
			}
			dto.setClaimsN(claimli);
		}else {
			dto.setClaimsN(null);
		}
		
		return dto;
	}

	@Override
	public InsurancePolicy_dto savePolicy(InsurancePolicy_dto dto) {
		InsurancePolicy policy=bindFrom_dto(dto);
		policy=policyRepository.save(policy);
		return bindFrom_policy(policy);
		//
	}

	@Override
	public List<InsurancePolicy_dto> getAllPolicy() {
		List<InsurancePolicy> li=policyRepository.findAll();
		List<InsurancePolicy_dto> dtoli=new ArrayList<InsurancePolicy_dto>();
		for (InsurancePolicy insurancePolicy : li) {
			dtoli.add(bindFrom_policy(insurancePolicy));
		}
		return dtoli;
	}

	@Override
	public InsurancePolicy_dto getPolicyById(Integer id) {
		InsurancePolicy policy=policyRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
		return bindFrom_policy(policy);
	}

	@Override
	public InsurancePolicy_dto updatePolicyById(Integer id,InsurancePolicy_dto dto) {
		InsurancePolicy policy=policyRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
		 policy=bindFrom_dto(dto);
		 policy.setPolicy_No(id);
		 policy=policyRepository.save(policy);
		 return bindFrom_policy(policy);
	}

	@Override
	public String deletePolicyById(Integer id) {
		InsurancePolicy policy=policyRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
		    policy.setClaims(null);
		    policyRepository.save(policy);
			policyRepository.deleteById(id);
			return "Record is successfully deleted.";
	}


}
