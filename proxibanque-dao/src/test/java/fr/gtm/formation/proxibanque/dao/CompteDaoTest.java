/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.util.JpaUtil;
import java.util.Collection;
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
public class CompteDaoTest
{

	private CompteDao compteDao;
	private final EntityManagerFactory emf = JpaUtil.getEmf();

	public long getNumberEntries()
	{
		EntityManager em = this.emf.createEntityManager();

		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
			Root<Compte> object = cQuery.from(Compte.class);

			cQuery.select(builder.count(object));

			TypedQuery<Long> query = em.createQuery(cQuery);
			return query.getSingleResult();
		}
		catch (Exception e)
		{
			Logger.getLogger(CompteDaoTest.class.getName()).log(Level.SEVERE, "ERREUR LECTURE TABLE", e);
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
		compteDao = new CompteDao(Compte.class);
	}

	@Test
	public void createTest()
	{
		System.out.println("******************************************************************************");
		System.out.println("this.getClass().getSimpleName() + \tCREATE TEST");
		System.out.println("******************************************************************************");
		try
		{
			Compte compte = new Compte();

			long nbBefore = this.getNumberEntries();
			compteDao.create(compte);
			long nbAfter = this.getNumberEntries();
			assertEquals(nbBefore + 1, nbAfter);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(CompteDaoTest.class.getName()).log(Level.SEVERE, "ERREUR CREATE TEST", ex);
			fail();
		}
	}

	//@Test
	public void updateTest()
	{
		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tUPDATE TEST");
		System.out.println("******************************************************************************");
		try
		{
			double solde = 50000000;
			Compte compte = compteDao.getCompteByNumero(1000);
			compte.setSolde(solde);
			compteDao.update(compte);

			Compte compteRecup = compteDao.getCompteByNumero(1000);
			assertEquals(compteRecup.getSolde(), solde, 0.1);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(CompteDaoTest.class.getName()).log(Level.SEVERE, "ERREUR UPDATE TEST", ex);
			fail();
		}
	}

	// Test réussi
	@Test
	public void deleteByPkTest()
	{
		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tDELETE BY PK TEST");
		System.out.println("******************************************************************************");

		try
		{
			long nbBefore = this.getNumberEntries();
			compteDao.deleteByPk(1003);
			long nbAfter = this.getNumberEntries();
			assertEquals(nbBefore - 1, nbAfter);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(CompteDaoTest.class.getName()).log(Level.SEVERE, "ERREUR DELETE TEST", ex);
			fail();
		}
	}

	@Test
	public void getComptebyClientTest()
	{

		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tgetComptebyClient TEST");
		System.out.println("******************************************************************************");
		try
		{
			EntityManager em = this.emf.createEntityManager();
			//recuperatiopn des compte à partir du client en faisant aoppel à la base de données
			Collection<Compte> listeCompteBDD = compteDao.getCompteByClient(em.find(Client.class, 4));
			assertEquals(2, listeCompteBDD.size());
			em.close();
		}
		catch (DaoException ex)
		{
			Logger.getLogger(CompteDaoTest.class.getName()).log(Level.SEVERE, "ERREUR CREATE TEST", ex);
			fail();
		}

	}

}
