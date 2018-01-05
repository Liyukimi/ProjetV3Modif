package com.objis.spring.demodatabase.dao;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.objis.spring.demodatabase.domaine.Client;
import com.objis.spring.demodatabase.domaine.Compte;
import com.objis.spring.demodatabase.domaine.Conseiller;

public interface ICompteDao extends JpaRepository<Compte, Integer>
{
	public Collection<Compte> findByClient(Client client);
	public Compte findByNumeroCompte(int numeroCompte);
	public List<Compte> findAll();
	public List<Compte> findAllByClient_Conseiller(Conseiller conseiller);
}
