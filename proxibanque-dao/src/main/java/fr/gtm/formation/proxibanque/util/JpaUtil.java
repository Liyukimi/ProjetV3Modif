package fr.gtm.formation.proxibanque.util;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class JpaUtil {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private JpaUtil() {
    }

    public static synchronized EntityManagerFactory getEmf() {
	if (emf == null) {
	    emf = Persistence.createEntityManagerFactory("proxibanquePU");
	}
	return emf;
    }

    public static synchronized void close() {
	if (emf != null) {
	    emf.close();
	    emf = null;
	}
    }

}
