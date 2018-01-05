package com.objis.spring.demodatabase.dao;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.objis.spring.demodatabase.domaine.Client;

@Repository("clientDao")
public interface IClientDao extends JpaRepository<Client, Integer> {

	public Collection<Client> findByConseiller_login(String login);
	
	public Client findByIdClient(int id);
	
	void deleteByIdClient(int id);
}
