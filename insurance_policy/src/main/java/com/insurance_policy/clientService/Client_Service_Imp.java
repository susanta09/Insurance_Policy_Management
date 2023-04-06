package com.insurance_policy.clientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance_policy.API_ExpectionHandler.ResourceNotFoundException;
import com.insurance_policy.clientRepository.ClientRepository;

import com.insurance_policy.dto.Client_dto;
import com.insurance_policy.model.Address;
import com.insurance_policy.model.Client;
import com.insurance_policy.model.InsurancePolicy;
import com.insurance_policy.policyRepository.InsurancePolicyRepository;
@Service
public class Client_Service_Imp implements Client_Service {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InsurancePolicyRepository policyRepository;

	public Client bindFrom_Dto(Client_dto dto)
	{
		Client client=new Client();
		client.setName(dto.getName());
		client.setPhoneNo(dto.getPhoneNo());
		client.setDob(dto.getDob());
		Address address=new Address();

		address.setCity(dto.getAddress().getCity());
		address.setState(dto.getAddress().getState());
		address.setCountry(dto.getAddress().getCountry());
		client.setAddress(address);
		List<InsurancePolicy> li=new ArrayList<InsurancePolicy>();
		List<Integer> pn=dto.getPolicy_No();
		if(pn!=null)
		{
			for (Integer integer : pn) {
				 InsurancePolicy policy=policyRepository.findById(integer)
						 .orElseThrow(()->new ResourceNotFoundException("Resources is no found "+integer));
					li.add(policy);
				}
		}
		else {
			li=null;
		}
		client.setPolicy(li);
		return client;	
	}
	public Client_dto bindFrom_Client(Client c)
	{
		Client_dto dto=new Client_dto();
		dto.setClient_Id(c.getClient_No());
		dto.setName(c.getName());
		dto.setPhoneNo(c.getPhoneNo());
		dto.setDob(c.getDob());
		com.insurance_policy.dto.Address address=new com.insurance_policy.dto.Address();
		
		address.setCity(c.getAddress().getCity());
		address.setState(c.getAddress().getState());
		address.setCountry(c.getAddress().getCountry());
		dto.setAddress(address);
		List<Integer> l=new ArrayList<Integer>();
		List<InsurancePolicy>lp=c.getPolicy();
		if(lp!=null)
		{
			for (InsurancePolicy Policy : lp) {
				l.add(Policy.getPolicy_No());
			}
		}
		else {
			l=null;
		}
		dto.setPolicy_No(l);
		return dto;
	}

	@Override
	public Client_dto saveClient(Client_dto dto) {
		System.out.println("+++++=");
		System.out.println(dto);
		Client client=new Client();
		client=bindFrom_Dto(dto);
		Client c=clientRepository.save(client);
		return bindFrom_Client(c);
	}

	@Override
	public List<Client_dto> getAllClient() {
		List<Client> li=clientRepository.findAll();
		List<Client_dto> dt=new ArrayList<Client_dto>();
		for (Client c : li) {
			Client_dto dto=new Client_dto();
			dto=bindFrom_Client(c);
			dt.add(dto);
		}
		return dt;
	}

	@Override
	public Client_dto getClientById(Long id) {
		Client c=(Client)clientRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
		return bindFrom_Client(c);
	}

	@Override
	public Client_dto updateClientById(Long id,Client_dto dto) {
		clientRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
			Client cl=bindFrom_Dto(dto);
			cl.setClient_No(id);
		    clientRepository.save(cl);
			return bindFrom_Client(cl);
	}

	@Override
	public String deleteClientById(Long id) {
		Client c=clientRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resources is no found "+id));
		c.setPolicy(null);
		clientRepository.save(c);
		clientRepository.deleteById(id);
		return "Record is successfully deleted.";

	}

	

}
