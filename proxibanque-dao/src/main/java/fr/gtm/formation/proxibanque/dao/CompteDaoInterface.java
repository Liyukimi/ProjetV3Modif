package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import java.util.Collection;

public interface CompteDaoInterface
{

	public Collection<Compte> getCompteByClient(Client client) throws DaoException;

	public void updateSolde(double solde, Compte compte) throws DaoException;

	public Compte getCompteByNumero(int numeroCompte) throws DaoException;

	public Collection<Compte> getAllComptes() throws DaoException;

	public Collection<Compte> getComptesByConseiller(Conseiller conseiller) throws DaoException;
}
