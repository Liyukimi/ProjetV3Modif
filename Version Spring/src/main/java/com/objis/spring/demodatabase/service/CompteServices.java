package com.objis.spring.demodatabase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objis.spring.demodatabase.dao.ICompteDao;
import com.objis.spring.demodatabase.domaine.Client;
import com.objis.spring.demodatabase.domaine.Compte;
import com.objis.spring.demodatabase.domaine.Conseiller;

/**
 * @author adminl Class CompteServices belongs to the layer Services. This class
 * allows to implement CompteServices Object. This class contains 2 methods
 * (getCompteByClient, virement). This class communicates with the layer Dao and
 * the layer Presentation.
 */
@Service("compteServices")
public class CompteServices implements ICompteServices
{
	@Autowired
	private ICompteDao compteDao;

	/**
	 * @param client
	 * @return This method returns a list of Compte Object. This method has 1
	 *         Object Client in parameter. This method gets back a Client object from
	 *         ListeComptes class and provides this object Client to the layer Dao with
	 *         the method getCompteByClient.
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 */
	public Collection<Compte> getCompteByClient(Client client)
	{
		// Return the CompteDao method getCompteByClient. Return an ArrayList of
		// Compte
		return compteDao.findByClient(client);
	}

	public Compte getCompteByNumero(int numeroCompte)
	{
		return compteDao.findByNumeroCompte(numeroCompte);
	}

	public Collection<Compte> getAllComptes() 
	{
		return compteDao.findAll();
	}

	public Collection<Compte> getComptesByConseiller(Conseiller conseiller) 
	{
		return compteDao.findAllByClient_Conseiller(conseiller);
		
	}
}
