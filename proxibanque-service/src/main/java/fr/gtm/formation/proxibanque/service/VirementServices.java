/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.service;

import fr.gtm.formation.proxibanque.dao.CompteDao;
import fr.gtm.formation.proxibanque.dao.VirementDao;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Virement;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * La classe VirementServices appartient au module service.
 * Elle permet d'instancier des objets VirementServices.
 * Elle possède 3 méthodes et communique avec les modules dao et presentation
 *
 * @author adminl
 */
public class VirementServices
{

	private VirementDao virementDao;
	private CompteDao compteDao;

	/**
	 * La méthode 'getVirementsByCompte' permet de récupérer la liste des virements
	 * effectués pour un compte donné.
	 * Pour cela elle fait appel à la méthode 'getVirementByCompte' du module dao.
	 *
	 * @param numeroCompte
	 * @param typeVirement
	 * @return Collection de virements
	 * @throws ServiceException
	 */
	public Collection<Virement> getVirementsByCompte(int numeroCompte, String typeVirement) throws ServiceException
	{
		virementDao = new VirementDao();
		Collection<Virement> listeVirements = new ArrayList<>();
		try
		{
			listeVirements = virementDao.getVirementsByCompte(numeroCompte, typeVirement);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(VirementServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}
		return listeVirements;
	}

	/**
	 * La méthode 'getComptesByVirement' permet de connaitre les comptes impliqués
	 * dans un virement donné.
	 * Pour cela elle fait appel à la méthode 'getComptesByVirement' du module dao
	 *
	 * @param idVirement
	 * @return collection de comptes
	 * @throws ServiceException
	 */
	public Collection<Compte> getComptesByViremenent(int idVirement) throws ServiceException
	{
		virementDao = new VirementDao();
		Collection<Compte> listeComptes = new ArrayList<>();
		try
		{
			listeComptes = virementDao.getComptesByViremenent(idVirement);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(VirementServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}
		return listeComptes;
	}

	/**
	 * La méthode 'createVirement' permet de créer un virement.
	 * Pour cela elle vérifie dans un premier temps que le compte debiteur et crediteur
	 * existe bien enfaisant appel à la méthode 'getCompteByNumero' du module dao.
	 * S'ils existent bien et qu'ils sont différents et que le solde du compte debiteur
	 * est suffisant pour effectuer le virement, le virement est créé en faisant appel
	 * à la méthode 'createVirement' du module dao.
	 *
	 * @param virement
	 * @param numCompteDebiteur
	 * @param numCompteCrediteur
	 * @param montant
	 * @return String
	 * @throws ServiceException
	 */
	public String createVirement(Virement virement, int numCompteDebiteur, int numCompteCrediteur, double montant) throws ServiceException
	{
		String message = "";

		compteDao = new CompteDao();
		virementDao = new VirementDao();
		Compte compteDebiteur;
		Compte compteCrediteur;
		double soldeDebiteur;
		double decouvert;

		// Récupère le compte à débiter
		try
		{
			compteDebiteur = compteDao.getCompteByNumero(numCompteDebiteur);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(VirementServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}

		// Récupère le compte à créditer
		try
		{
			compteCrediteur = compteDao.getCompteByNumero(numCompteCrediteur);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(VirementServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}

		// Vérifie si les comptes ne sont pas identiques
		if (compteDebiteur.getNumeroCompte() == compteCrediteur.getNumeroCompte())
		{
			message = "Les deux comptes sont identiques. Impossible d'effectuer un virement vers un même compte";
		}
		else
		{
			soldeDebiteur = compteDebiteur.getSolde();
			decouvert = compteDebiteur.getDecouvert();
			// Vérifie que le compte peut être crédité
			if (soldeDebiteur - montant >= -decouvert)
			{
				try
				{
					//Ajout des comptes au virement
					virement.setCompteDebiteur(compteDebiteur);
					virement.setCompteCrediteur(compteCrediteur);
					virementDao.createVirement(virement, montant);
					message = "Transaction effectuée";
					org.apache.logging.log4j.Logger log = LogManager.getLogger(VirementServices.class);
					log.trace("Virement du compte débiteur n°{} au compte créditeur n°{} de {} €", numCompteDebiteur, numCompteCrediteur, montant);

				}
				catch (DaoException ex)
				{
					Logger.getLogger(VirementServices.class.getName()).log(Level.SEVERE, null, ex);
					throw new ServiceException("Erreur lors de la transaction, le virement n'a pas été réalisé");
				}
			}
			// Le compte ne peut pas être crédité
			else
			{
				message = "Attention, le compte à débiter n'a pas un solde suffisant pour effectuer un virement de " + montant + " euros";
			}
		}

		return message;
	}

}