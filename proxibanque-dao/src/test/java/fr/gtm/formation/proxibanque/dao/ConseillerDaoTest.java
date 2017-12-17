/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.util.JpaUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adminl
 */
public class ConseillerDaoTest
{

	private ConseillerDao conseillerDao;
	private final EntityManagerFactory emf = JpaUtil.getEmf();

	public long getNumberEntries()
	{
		EntityManager em = this.emf.createEntityManager();

		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
			Root<Conseiller> object = cQuery.from(Conseiller.class);

			cQuery.select(builder.count(object));

			TypedQuery<Long> query = em.createQuery(cQuery);
			return query.getSingleResult();
		}
		catch (Exception e)
		{
			Logger.getLogger(ConseillerDaoTest.class.getName()).log(Level.SEVERE, "ERREUR LECTURE TABLE", e);
		}
		finally
		{
			em.close();
		}
		return 0;
	}

	@Before
	public void initTestParam()
	{
		conseillerDao = new ConseillerDao(Conseiller.class);
	}

	@Test
	public void createTest()
	{
		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tCREATE TEST");
		System.out.println("******************************************************************************");
		try
		{
			Conseiller conseiller = new Conseiller("logintest", "passwordtest");
			long nbBefore = this.getNumberEntries();
			conseillerDao.create(conseiller);
			long nbAfter = this.getNumberEntries();
			assertEquals(nbBefore + 1, nbAfter);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(ClientDaoTest.class.getName()).log(Level.SEVERE, "ERREUR CREATE TEST", ex);
			fail();
		}
	}

	@Test
	public void updateTest()
	{
		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tUPDATE TEST");
		System.out.println("******************************************************************************");
		try
		{
			Conseiller conseiller = conseillerDao.getConseillerByLogin("Conseiller1");
			conseiller.setPrenom("nouveauPrenom");
			conseillerDao.update(conseiller);

			assertEquals(conseiller.getPrenom(), "nouveauPrenom");
		}
		catch (DaoException ex)
		{
			Logger.getLogger(ConseillerDaoTest.class.getName()).log(Level.SEVERE, "ERREUR UPDATE TEST", ex);
			fail();
		}
	}

	@Test
	public void deleteByPkTest()
	{
		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tDELETE BY PK TEST");
		System.out.println("******************************************************************************");

		try
		{
			long nbBefore = this.getNumberEntries();
			conseillerDao.deleteByPk("Conseiller3");
			long nbAfter = this.getNumberEntries();

			assertEquals(nbBefore - 1, nbAfter);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(ConseillerDaoTest.class.getName()).log(Level.SEVERE, "ERREUR DELETE TEST", ex);
			fail();
		}
	}

}
