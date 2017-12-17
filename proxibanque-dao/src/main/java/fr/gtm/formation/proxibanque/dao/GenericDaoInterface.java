package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Interface générique permettant d'implémenter les différentes opérations du
 * CRUD
 * </p>
 *
 * @author Lise Rodier
 * @param <T>
 */
public interface GenericDaoInterface<T, PK extends Serializable>
{

	/**
	 * Persistance de l'objet en base
	 *
	 * @param object : l'objet à inscrire en base
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public void create(T object) throws DaoException;

	/**
	 * Lecture de l'objet dont la clé primaire est passée en paramètre
	 *
	 * @param primaryKey : la clé primaire de l'objet recherché
	 * @return l'objet cherché
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public T read(PK primaryKey) throws DaoException;

	/**
	 * Retourne une liste de tous les objets de la table
	 *
	 * @return liste d'objets
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public List<T> readAll() throws DaoException;

	/**
	 * update un objet passé en paramètre
	 *
	 * @param object : l'objet à modifier
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public void update(T object) throws DaoException;

	/**
	 * Supprime l'objet passé en paramètre de la base de données
	 *
	 * @param object : l'objet à supprimer
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	public void delete(T object) throws DaoException;

	/**
	 *
	 * @param primaryKey
	 * @throws DaoException
	 */
	public void deleteByPk(PK primaryKey) throws DaoException;
}
