package com.objis.spring.demodatabase.service;

import javax.persistence.NoResultException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.objis.spring.demodatabase.dao.IConseillerDao;
import com.objis.spring.demodatabase.domaine.Conseiller;

/**
 * la classe ConseillerServices appartient au module service. Elle permet
 * d'instancier des objets ConseillerServices. Elle contient 2 méthodes et
 * communique avec les modules dao et presentation
 *
 * @author adminl
 */
@Service("conseillerServices")
public class ConseillerServices implements IConseillerServices {
	@Autowired
	private IConseillerDao conseillerDao;

	/**
	 * cette méthode permet de vérifier l'existence d'un conseiller en fonction de
	 * son login et la validité du mot de passe. Pour cela elle fait appel à la
	 * méthode 'getConseillerByLogin' du module DAO.
	 *
	 * @param conseiller
	 * @return boolean
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceNullWarning
	 */
	public boolean conseillerConnection(Conseiller conseiller) {

		String passwordGiven;
		String passwordRecup;
		String login = conseiller.getLogin();

		try {
			Conseiller conseillerBD = conseillerDao.findByLogin(login);
			if (conseillerBD != null) {

				passwordRecup = conseillerBD.getPassword();
				passwordGiven = conseiller.getPassword();

				if (passwordRecup != null & passwordRecup.equals(passwordGiven)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (DataAccessException e) {
			Logger.getLogger(ConseillerServices.class.getName()).log(Level.ERROR,
					"Erreur lors de l'exécution " + e.getMostSpecificCause(), e);
		}

		return false;

	}

	/**
	 * cette méthode permet de récupérer les informations d'un conseiller par le
	 * biais de son login. Pour cela elle fait appel à la méthode
	 * 'getConseillerByLogin' du module dao.
	 *
	 * @param login
	 * @return conseiller
	 * @throws ServiceException
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceNullWarning
	 */
	public Conseiller getConseillerByLogin(String login) {
		try {
			return conseillerDao.findByLogin(login);
		} catch (EmptyResultDataAccessException e) {
			Logger.getLogger(ConseillerServices.class.getName()).log(Level.WARN,
					"Il n'existe pas de conseiller dont le login est " + login, e);
		} catch (DataAccessException e) {
			Logger.getLogger(ConseillerServices.class.getName()).log(Level.ERROR,
					"Erreur lors de l'exécution " + e.getMostSpecificCause(), e);
		}
		return null;
	}
}
