package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.domaine.Compte;
//import fr.gtm.formation.proxibanque.domaine.Compte_;
import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author adminl Class CompteDao belongs to the layer DAO. This class allows to
 * implement CompteDao Object. This class contains 3 methods (getCompteByClient,
 * getCompteByNumero, updateSolde). This class communicates with the Database
 * and the layer Services.
 */
public class CompteDao extends GenericDaoImpl<Compte, Integer> implements CompteDaoInterface
{

	public CompteDao(Class<Compte> entityType)
	{
		super(entityType);
	}

	public CompteDao()
	{
		super(Compte.class);
	}

	/**
	 * @param client
	 * @return This method returns a list of comptes This method has 1 object
	 *         client in parameter This method gets back from the database a list of
	 *         comptes linked to the Object client in parameter.
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	@Override
	public Collection<Compte> getCompteByClient(Client client) throws DaoException
	{
		Collection<Compte> listeComptes = null;

		EntityManager em = this.emf.createEntityManager();

		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Compte> cQuery = builder.createQuery(Compte.class);
			Root<Compte> object = cQuery.from(Compte.class);

			cQuery.where(builder.equal(object.get("client"), client));

			TypedQuery<Compte> query = em.createQuery(cQuery);
			listeComptes = query.getResultList();
		}
		finally
		{
			em.close();
		}
		return listeComptes;
	}

	/**
	 * @param numeroCompte
	 * @return This method returns a object Comptes This method has 1 object
	 *         Compte in parameter This method gets back from the database a object
	 *         Comptes linked to the compteNumber given by the Object Compte in
	 *         parameter.
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	@Override
	public Compte getCompteByNumero(int numeroCompte) throws DaoException
	{
		return super.read(numeroCompte);
	}

	/**
	 * This method update the column solde from table Compte with the number
	 * solde given in parameter. The row update are linked to the compteNumber
	 * giver by the object Compte in parameter.
	 *
	 * @param solde, compte Compte in parameter and number solde.
	 * @param compte
	 * @throws fr.gtm.formation.proxibanque.dao.exceptions.DaoException
	 */
	@Override
	public void updateSolde(double solde, Compte compte) throws DaoException
	{
		compte.setSolde(solde);
		super.update(compte);
	}

}
