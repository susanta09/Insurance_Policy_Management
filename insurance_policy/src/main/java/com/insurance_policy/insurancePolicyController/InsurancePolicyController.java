package com.insurance_policy.insurancePolicyController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance_policy.API_ExpectionHandler.ResourceNotFoundException;
import com.insurance_policy.dto.InsurancePolicy_dto;
import com.insurance_policy.policyService.InsurancePolicy_Service;


@RestController
@RequestMapping("/api")
public class InsurancePolicyController {
	@Autowired
	private InsurancePolicy_Service policy_Service;

	@PostMapping("/policies")
	public ResponseEntity<InsurancePolicy_dto> addClientData(@RequestBody @Valid InsurancePolicy_dto policy)
	{	
		 return new ResponseEntity<InsurancePolicy_dto>( policy_Service.savePolicy(policy),HttpStatus.CREATED);
	}
	@GetMapping("/policies")
	public List<InsurancePolicy_dto> getAll()
	{
		 return policy_Service.getAllPolicy();
	}

	@GetMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy_dto> getById(@PathVariable("id") Integer id)throws ResourceNotFoundException
	{
		return new ResponseEntity<InsurancePolicy_dto>(policy_Service.getPolicyById(id),HttpStatus.CREATED);
	}
	@PutMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy_dto> updateById(@PathVariable("id") Integer id,@RequestBody @Valid InsurancePolicy_dto dto)throws ResourceNotFoundException
	{
		return new ResponseEntity<InsurancePolicy_dto>(policy_Service.updatePolicyById(id,dto),HttpStatus.CREATED);
	}
	@DeleteMapping("/policies/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(policy_Service.deletePolicyById(id));
	}
	

}
