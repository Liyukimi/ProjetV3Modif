package fr.gtm.formation.proxibanque.service;

import fr.gtm.formation.proxibanque.dao.CompteDao;
import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adminl Class CompteServices belongs to the layer Services. This class
 * allows to implement CompteServices Object. This class contains 2 methods
 * (getCompteByClient, virement). This class communicates with the layer Dao and
 * the layer Presentation.
 */
public class CompteServices
{
	private CompteDao compteDao;

	/**
	 * @param client
	 * @return This method returns a list of Compte Object. This method has 1
	 *         Object Client in parameter. This method gets back a Client object from
	 *         ListeComptes class and provides this object Client to the layer Dao with
	 *         the method getCompteByClient.
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 */
	public Collection<Compte> getCompteByClient(Client client) throws ServiceException
	{
		// Return the CompteDao method getCompteByClient. Return an ArrayList of
		// Compte
		compteDao = new CompteDao();
		Collection<Compte> listeComptes = new ArrayList<>();
		try
		{
			listeComptes = compteDao.getCompteByClient(client);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(CompteServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}

		return listeComptes;
	}

	public Compte getCompteByNumero(int numeroCompte) throws ServiceException
	{
		compteDao = new CompteDao();
		Compte compte = null;
		try
		{
			compte = compteDao.getCompteByNumero(numeroCompte);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(CompteServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}
		return compte;
	}
}
