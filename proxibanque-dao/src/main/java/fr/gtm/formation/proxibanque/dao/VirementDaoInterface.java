package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Virement;
import java.util.Collection;

public interface VirementDaoInterface
{
	public Collection getComptesByViremenent(Virement virement) throws DaoException;

	public Collection getVirementsByCompte(Compte compte, String typeVirement) throws DaoException;
}
