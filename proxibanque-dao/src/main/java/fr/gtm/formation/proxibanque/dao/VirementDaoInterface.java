package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import java.util.Collection;

public interface VirementDaoInterface
{
	public Collection getComptesByViremenent(int idVirement) throws DaoException;

	public Collection getVirementsByCompte(int numeroCompte, String typeVirement) throws DaoException;
}
