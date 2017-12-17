package fr.gtm.formation.proxibanque.service;

import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.dao.ConseillerDao;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * la classe ConseillerServices appartient au module service. Elle permet
 * d'instancier des objets ConseillerServices.
 * Elle contient 2 méthodes et communique avec les modules dao et presentation
 *
 * @author adminl
 */
public class ConseillerServices
{
	private ConseillerDao conseillerDao;

	/**
	 * cette méthode permet de vérifier l'existence d'un conseiller en
	 * fonction de son login et la validité du mot de passe.
	 * Pour cela elle fait appel à la méthode 'getConseillerByLogin' du module DAO.
	 *
	 * @param conseiller
	 * @return boolean
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceNullWarning
	 */
	public boolean conseillerConnection(Conseiller conseiller) throws ServiceException
	{

		String passwordServeur;
		String passwordDao;
		String login = conseiller.getLogin();
		try
		{
			conseillerDao = new ConseillerDao();
			Conseiller conseillerBD = conseillerDao.getConseillerByLogin(login);

			if (conseillerBD != null)
			{
				passwordDao = conseillerBD.getPassword();
				passwordServeur = conseiller.getPassword();

				if (passwordDao != null && passwordDao.equals(passwordServeur))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

		}
		catch (DaoException ex)
		{
			Logger.getLogger(ConseillerServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}
		return false;
	}

	/**
	 * cette méthode permet de récupérer les informations d'un conseiller par le biais
	 * de son login.
	 * Pour cela elle fait appel à la méthode 'getConseillerByLogin' du module dao.
	 *
	 * @param login
	 * @return conseiller
	 * @throws ServiceException
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceNullWarning
	 */
	public Conseiller getConseillerByLogin(String login) throws ServiceException
	{
		conseillerDao = new ConseillerDao();
		try
		{
			return conseillerDao.getConseillerByLogin(login);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(ConseillerServices.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServiceException(ex.getMessage(), ex.getCause());
		}
	}
}
