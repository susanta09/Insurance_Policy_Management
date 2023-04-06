package com.insurance_policy.clientController;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance_policy.API_ExpectionHandler.ResourceNotFoundException;
import com.insurance_policy.clientService.Client_Service;
import com.insurance_policy.dto.Client_dto;


@RestController
@RequestMapping("/api")
public class ClientController {
	@Autowired
	private Client_Service client_Service;
	
	@PostMapping("/clients")
	public ResponseEntity<Client_dto> addClientData(@RequestBody @Valid Client_dto dto)
	{	System.out.println(dto);
		 return new ResponseEntity<Client_dto>(client_Service.saveClient(dto),HttpStatus.CREATED);
	}
	@GetMapping("/clients")
	public List<Client_dto> getAll()
	{
		 return client_Service.getAllClient();
	}
	@ResponseBody
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client_dto> getById(@PathVariable("id") Long c_Id)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(client_Service.getClientById(c_Id));
		
	}
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client_dto> updateById(@PathVariable("id") Long c_Id,@RequestBody Client_dto dto)throws ResourceNotFoundException
	{
		return new ResponseEntity<Client_dto>(client_Service.updateClientById(c_Id, dto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long c_Id)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(client_Service.deleteClientById(c_Id));
		
	}
	

}
