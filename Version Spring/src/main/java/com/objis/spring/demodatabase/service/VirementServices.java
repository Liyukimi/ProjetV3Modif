package com.objis.spring.demodatabase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objis.spring.demodatabase.dao.ICompteDao;
import com.objis.spring.demodatabase.dao.IVirementDao;
import com.objis.spring.demodatabase.domaine.Compte;
import com.objis.spring.demodatabase.domaine.Conseiller;
import com.objis.spring.demodatabase.domaine.Virement;

/**
 * La classe VirementServices appartient au module service.
 * Elle permet d'instancier des objets VirementServices.
 * Elle possède 3 méthodes et communique avec les modules dao et presentation
 *
 * @author adminl
 */
@Service("virementServices")
public class VirementServices implements IVirementServices
{

	@Autowired
	private IVirementDao virementDao;
	@Autowired
	private ICompteDao compteDao;

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
	public Collection<Virement> getVirementsByCompte(Compte compte, String typeVirement) 
	{
		if(typeVirement == "debit")
			return virementDao.findAllByCompteDebiteur(compte);
		else if(typeVirement == "credit")
			return virementDao.findAllByCompteCrediteur(compte);
		
		return null;
	}

	public Collection<Virement> getVirementsByConseiller(Conseiller conseiller) 
	{
		return virementDao.findAllByConseiller(conseiller);
		
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
	public String createVirement(Virement virement, int numCompteDebiteur, int numCompteCrediteur, double montant)
	{

		Compte compteDebiteur;
		Compte compteCrediteur;
		double soldeDebiteur;
		double decouvert;

		// Récupère le compte à débiter
		compteDebiteur = compteDao.findByNumeroCompte(numCompteDebiteur);
		

		// Récupère le compte à créditer
		compteCrediteur = compteDao.findByNumeroCompte(numCompteCrediteur);
		

		// Vérifie si les comptes ne sont pas identiques
		if((compteDebiteur!=null && compteCrediteur!=null) & (compteDebiteur.getNumeroCompte() == compteCrediteur.getNumeroCompte()))
		{
			return "Les deux comptes sont identiques. Impossible d'effectuer un virement vers un même compte";
		}
		else
		{
			soldeDebiteur = compteDebiteur.getSolde();
			decouvert = compteDebiteur.getDecouvert();
			// Vérifie que le compte peut être crédité
			if (soldeDebiteur - montant >= -decouvert)
			{
					//Ajout des comptes au virement
					virement.setCompteDebiteur(compteDebiteur);
					virement.setCompteCrediteur(compteCrediteur);
					virement.setMontant(montant);
					if(virementDao.save(virement) != null)
						return "Transaction effectuée";
			
			}
			// Le compte ne peut pas être crédité
			else
			{
				return "Le compte à débiter n'a pas un solde suffisant pour effectuer un virement de " + montant + " euros";
			}
		}
		
		return "Erreur lors de la transaction, le virement n'a pas été réalisé";
	}

}
