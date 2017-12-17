package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;

public interface ConseillerDaoInterface
{

	/**
	 * @param login
	 * @return This method returns a object Conseiller. This method has 1 char
	 *         parameter : login. This method gets back from the database a
	 *         object Conseiller linked to the login given in parameter.
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public Conseiller getConseillerByLogin(String login) throws DaoException;
}
