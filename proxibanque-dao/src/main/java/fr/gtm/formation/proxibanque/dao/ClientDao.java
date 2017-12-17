package fr.gtm.formation.proxibanque.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Client;
//import fr.gtm.formation.proxibanque.domaine.Client_;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ClientDao extends GenericDaoImpl<Client, Integer> implements ClientDaoInterface
{

	public ClientDao(Class<Client> entityType)
	{
		super(entityType);
	}

	public ClientDao()
	{
		super(Client.class);
	}

	/**
	 *
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	@Override
	public Collection<Client> getClientsByConseiller(String login) throws DaoException
	{
		Collection<Client> listeClients;

		EntityManager em = this.emf.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Client> cQuery = builder.createQuery(Client.class);
		Root<Client> client = cQuery.from(Client.class);
		Join<Client, Conseiller> conseillerJoin = client.join("conseiller");
		cQuery.where(builder.equal(conseillerJoin.get("login"), login));

		TypedQuery<Client> query = em.createQuery(cQuery);
		listeClients = query.getResultList();

		em.close();
		return listeClients;
	}

	@Override
	public Client getClientById(int id) throws DaoException
	{
		return super.read(id);
	}

	@Override
	public void delete(Client object) throws DaoException
	{
		super.deleteByPk(object.getIdClient());
	}

	@Override
	public void update(Client object) throws DaoException
	{
		String sql = "UPDATE client SET ";
		// if attribute is null it's not include in the update request
		if (!object.getPrenom().isEmpty())
		{
			sql += "prenom = '" + object.getPrenom() + "'";
		}
		if (!object.getNom().isEmpty())
		{
			// if another attribute has been include
			if (!sql.endsWith("SET "))
			{
				sql += ", ";
			}
			sql += "nom = '" + object.getNom() + "'";
		}
		if (!object.getAdresse().isEmpty())
		{
			// if another attribute has been include
			if (!sql.endsWith("SET "))
			{
				sql += ", ";
			}
			sql += "adresse = '" + object.getAdresse() + "'";
		}
		if (!object.getCodePostal().isEmpty())
		{
			// if another attribute has been include
			if (!sql.endsWith("SET "))
			{
				sql += ", ";
			}
			sql += "code_postal = " + object.getCodePostal();
		}
		if (!object.getVille().isEmpty())
		{
			// if another attribute has been include
			if (!sql.endsWith("SET "))
			{
				sql += ", ";
			}
			sql += "ville = '" + object.getVille() + "'";
		}
		if (!object.getMail().isEmpty())
		{
			// if another attribute has been include
			if (!sql.endsWith("SET "))
			{
				sql += ", ";
			}
			sql += "mail = '" + object.getMail();
		}
		sql += " WHERE id_client = " + object.getIdClient();

		System.out.println(sql);
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();

		tx.commit();
		em.close();
	}

}
