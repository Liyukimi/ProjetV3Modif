package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import java.util.Collection;

public interface ClientDaoInterface
{
	public Collection<Client> getClientsByConseiller(String login) throws DaoException;

	public Client getClientById(int id) throws DaoException;
}
