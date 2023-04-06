package com.insurance_policy.claim_Controller;

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
import com.insurance_policy.claim_Service.ClaimService;
import com.insurance_policy.dto.Claim_dto;
@RestController
@RequestMapping("/api")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	@PostMapping("/claims")
	public ResponseEntity<Claim_dto> addClaim(@RequestBody @Valid Claim_dto dto)
	{
		return new ResponseEntity<Claim_dto>(claimService.saveClaim(dto),HttpStatus.CREATED);
	}
	@GetMapping("/claims")
	public List<Claim_dto> getAll()
	{
		return claimService.getAllClaim();
	}
	@GetMapping("/claims/{id}")
	public ResponseEntity<Claim_dto> getById(@PathVariable("id") Integer id)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(claimService.getClaimById(id));
	}
	@PutMapping("/claims/{id}")
	public ResponseEntity<Claim_dto> updateById(@PathVariable("id") Integer id,@RequestBody @Valid Claim_dto dto)throws ResourceNotFoundException
	{
		return new ResponseEntity<Claim_dto>(claimService.updateClaimById(id, dto),HttpStatus.CREATED);
	}
	

	@DeleteMapping("/claims/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(claimService.deleteClaimById(id));
	}

}
