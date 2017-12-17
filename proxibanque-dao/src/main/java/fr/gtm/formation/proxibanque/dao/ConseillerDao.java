package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;

/**
 * @author adminl Class AdivisorDao belongs to the layer DAO. This class allows
 * to implement AdivisorDao Object. This class contains 1 method :
 * getConseillerByLogin. This class communicates with the Database and the layer
 * Services.
 */
public class ConseillerDao extends GenericDaoImpl<Conseiller, String> implements ConseillerDaoInterface
{

	public ConseillerDao(Class<Conseiller> entityType)
	{
		super(entityType);
	}

	public ConseillerDao()
	{
		super(Conseiller.class);
	}

	/**
	 * @param login
	 * @return This method returns a object Conseiller. This method has 1 char
	 *         parameter : login. This method gets back from the database a object
	 *         Conseiller linked to the login given in parameter.
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	@Override
	public Conseiller getConseillerByLogin(String login) throws DaoException
	{
		return this.read(login);
	}

}
