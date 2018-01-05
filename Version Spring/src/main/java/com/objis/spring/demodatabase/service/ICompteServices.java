package com.objis.spring.demodatabase.service;

import java.util.Collection;

import com.objis.spring.demodatabase.domaine.Client;
import com.objis.spring.demodatabase.domaine.Compte;
import com.objis.spring.demodatabase.domaine.Conseiller;

public interface ICompteServices
{
	public Collection<Compte> getCompteByClient(Client client);
	public Compte getCompteByNumero(int numeroCompte);

	public Collection<Compte> getAllComptes();

	public Collection<Compte> getComptesByConseiller(Conseiller conseiller);
}
