/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.util.JpaUtil;
import java.io.Serializable;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDaoInterface<T, PK>
{

	protected EntityManagerFactory emf;
	protected Class<T> entityType;

	public GenericDaoImpl(Class<T> entityType)
	{
		emf = JpaUtil.getEmf();
		this.entityType = entityType;
	}

	@Override
	public void create(T object) throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try
		{
			tx.begin();
			em.persist(object);
			tx.commit();
		}
		catch (EntityExistsException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("Le " + entityType.getSimpleName().toLowerCase() + " existe déja", e);
		}
		catch (IllegalArgumentException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("L'élément fourni n'est pas une entité valide", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public T read(PK primaryKey) throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			return (T) em.find(entityType, primaryKey);
		}
		catch (IllegalArgumentException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("L'élément fourni n'est pas une entité valide", e);
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("Erreur lors de la lecture du " + entityType.getSimpleName().toLowerCase(), e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public List<T> readAll() throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();

		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<T> cQuery = builder.createQuery(entityType);
			Root<T> object = cQuery.from(entityType);
			cQuery.select(object);

			TypedQuery<T> query = em.createQuery(cQuery);

			return query.getResultList();
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void update(T object) throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.merge(object);
			tx.commit();
		}
		catch (IllegalArgumentException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("L'élément fourni n'est pas une entité valide", e);
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("Le " + entityType.getSimpleName().toLowerCase() + " n'a pas été mis à jour", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void deleteByPk(PK primaryKey) throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try
		{
			tx.begin();
			em.remove(em.find(entityType, primaryKey));
			tx.commit();
		}
		catch (IllegalArgumentException e)
		{
			throw new DaoException("L'élément fourni n'est pas une entité valide");
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void delete(T object) throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try
		{
			tx.begin();
			object = em.merge(object);
			em.remove(object);
			tx.commit();
		}
		catch (IllegalArgumentException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("L'élément fourni n'est pas une entité valide", e);
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException("Le " + entityType.getSimpleName().toLowerCase() + " n'a pas été supprimé ", e);
		}
		finally
		{
			em.close();
		}
	}

}
