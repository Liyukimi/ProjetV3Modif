package fr.gtm.formation.proxibanque.service;
//import org.junit.Test;

//à utiliser si utilisation des annotations @Mock
import fr.gtm.formation.proxibanque.dao.ConseillerDao;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConseillerServiceTest
{

	@Test
	public void getConseillerByLoginTest() throws DaoException
	{
		//1. arrange (given)  System Under Test and mocks initialization and configuration
		//Mock ConseillerDao et associe à l'appel de getConseillerByLogin("login") un retour égal à conseillerGenere
		ConseillerDao conseillerMock = mock(ConseillerDao.class);
		Conseiller conseillerGenere = new Conseiller("login", "password");
		when(conseillerMock.getConseillerByLogin("login")).thenReturn(conseillerGenere);

		//2. act (when)	An operation which is a subject to testing; preferably only one operation on an SUT
		// Appel la méthode getConseillerByLogin et stock le résultat
		Conseiller conseillerResult = conseillerMock.getConseillerByLogin("login");

		//3. assert (then)	The assertion and verification phase
		// Teste si l'conseiller retourné est bien égal à celui donné
		assertSame(conseillerGenere, conseillerResult);
	}
}
