/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Virement;
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

/**
 *
 * @author adminl
 */
public class VirementDaoTest
{

	private VirementDao virementDao;
	private final EntityManagerFactory emf = JpaUtil.getEmf();

	public long getNumberEntries()
	{
		EntityManager em = this.emf.createEntityManager();

		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
			Root<Virement> object = cQuery.from(Virement.class);

			cQuery.select(builder.count(object));

			TypedQuery<Long> query = em.createQuery(cQuery);
			return query.getSingleResult();
		}
		catch (Exception e)
		{
			Logger.getLogger(VirementDaoTest.class.getName()).log(Level.SEVERE, "ERREUR LECTURE TABLE", e);
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
		virementDao = new VirementDao(Virement.class);
	}

	// @Test
	public void createTest()
	{
		System.out.println("******************************************************************************");
		System.out.println(this.getClass().getSimpleName() + "\tcreateVirement TEST");
		System.out.println("******************************************************************************");
		EntityManager em = this.emf.createEntityManager();
		try
		{
			long nbBefore = this.getNumberEntries();
			virementDao.createVirement(em.find(Virement.class, 1), 50);
			long nbAfter = this.getNumberEntries();
			assertEquals(nbBefore + 1, nbAfter);
		}
		catch (DaoException ex)
		{
			Logger.getLogger(VirementDaoTest.class.getName()).log(Level.SEVERE, "ERREUR createVirement TEST", ex);
			fail();
		}
		finally
		{
			em.close();
		}
	}

}
