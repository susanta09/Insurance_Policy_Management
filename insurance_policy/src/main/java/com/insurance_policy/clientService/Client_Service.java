package com.insurance_policy.clientService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance_policy.dto.Client_dto;
import com.insurance_policy.model.Client;
@Service
public interface Client_Service {
	public Client_dto saveClient(Client_dto dto);
	public List<Client_dto> getAllClient();
//	public Client getClientById(Long id);
	public Client_dto getClientById(Long id);
	public Client_dto updateClientById(Long id,Client_dto dto);
	public String deleteClientById(Long id);
}
