package com.insurance_policy.clientRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance_policy.model.Client;
public interface ClientRepository extends JpaRepository<Client, Long> {

}
