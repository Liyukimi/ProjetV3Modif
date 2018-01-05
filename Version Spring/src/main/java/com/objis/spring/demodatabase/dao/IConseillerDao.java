package com.objis.spring.demodatabase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.objis.spring.demodatabase.domaine.Conseiller;

@Repository("conseillerDao")
public interface IConseillerDao extends JpaRepository<Conseiller, String> {

	/**
	 * @param login
	 * @return This method returns a object Conseiller. This method has 1 char
	 *         parameter : login. This method gets back from the database a object
	 *         Conseiller linked to the login given in parameter.
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public Conseiller findByLogin(String login);
	// public List<Compte> findAllBylisteClients_listeComptes();
}
