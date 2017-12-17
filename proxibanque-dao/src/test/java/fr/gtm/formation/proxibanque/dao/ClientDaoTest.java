package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
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

public class ClientDaoTest {

    private ClientDao clientDao;
    private final EntityManagerFactory emf = JpaUtil.getEmf();

    public long getNumberEntries() {
	EntityManager em = this.emf.createEntityManager();

	try {
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
	    Root<Client> object = cQuery.from(Client.class);

	    cQuery.select(builder.count(object));

	    TypedQuery<Long> query = em.createQuery(cQuery);
	    return query.getSingleResult();
	} catch (Exception e) {
	    Logger.getLogger(ClientDaoTest.class.getName()).log(Level.SEVERE, "ERREUR LECTURE TABLE", e);
	} finally {
	    em.close();
	}
	return 0;
    }

    @Before
    public void initTestParam() {
	clientDao = new ClientDao(Client.class);
    }

    @Test
    public void createTest() {
	System.out.println("******************************************************************************");
	System.out.println(this.getClass().getSimpleName() + "\tCREATE TEST");
	System.out.println("******************************************************************************");
	try {
	    Client client = new Client("nom", "prenom");
	    long nbBefore = this.getNumberEntries();
	    clientDao.create(client);
	    long nbAfter = this.getNumberEntries();
	    assertEquals(nbBefore + 1, nbAfter);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientDaoTest.class.getName()).log(Level.SEVERE, "ERREUR CREATE TEST", ex);
	    fail();
	}
    }

    //@Test
    public void updateTest() {
	System.out.println("******************************************************************************");
	System.out.println(this.getClass().getSimpleName() + "\tUPDATE TEST");
	System.out.println("******************************************************************************");
	EntityManager em = this.emf.createEntityManager();
	try {
	    Client client = em.find(Client.class, 3);
	    client.setPrenom("nouveauPrenom");
	    clientDao.update(client);

	    Client clientRecup = em.find(Client.class, 3);

	    assertEquals(clientRecup.getPrenom(), "nouveauPrenom");
	} catch (DaoException ex) {
	    Logger.getLogger(ClientDaoTest.class.getName()).log(Level.SEVERE, "ERREUR UPDATE TEST", ex);
	    fail();
	} finally {
	    em.close();
	}
    }

    @Test
    public void deleteByPkTest() {
	System.out.println("******************************************************************************");
	System.out.println(this.getClass().getSimpleName() + "\tDELETE BY PK TEST");
	System.out.println("******************************************************************************");

	try {
	    long nbBefore = this.getNumberEntries();
	    clientDao.deleteByPk(1);
	    long nbAfter = this.getNumberEntries();
	    assertEquals(nbBefore - 1, nbAfter);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientDaoTest.class.getName()).log(Level.SEVERE, "ERREUR DELETE TEST", ex);
	    fail();
	}
    }

    @Test
    public void deleteTest() {
	System.out.println("******************************************************************************");
	System.out.println(this.getClass().getSimpleName() + "\tDELETE TEST");
	System.out.println("******************************************************************************");
	EntityManager em = this.emf.createEntityManager();
	try {
	    long nbBefore = this.getNumberEntries();
	    clientDao.delete(em.find(Client.class, 2));
	    long nbAfter = this.getNumberEntries();
	    assertEquals(nbBefore - 1, nbAfter);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientDaoTest.class.getName()).log(Level.SEVERE, "ERREUR DELETE TEST", ex);
	    fail();
	} finally {
	    em.close();
	}
    }

}
