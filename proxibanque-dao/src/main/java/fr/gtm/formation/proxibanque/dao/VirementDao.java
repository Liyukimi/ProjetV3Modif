package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Virement;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class VirementDao extends GenericDaoImpl<Virement, Integer> implements VirementDaoInterface
{
	public VirementDao(Class<Virement> entityType)
	{
		super(entityType);
	}

	public VirementDao()
	{
		super(Virement.class);
	}

	@Override
	public Collection getComptesByViremenent(int idVirement) throws DaoException
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Collection getVirementsByCompte(int numeroCompte, String typeVirement) throws DaoException
	{
		Collection<Virement> listeVirements = null;

		EntityManager em = this.emf.createEntityManager();

		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Virement> cQuery = builder.createQuery(Virement.class);
			Root<Virement> virement = cQuery.from(Virement.class);
			switch (typeVirement)
			{
				case "debit":
					cQuery.where(builder.equal(virement.get("compteDebiteur"), numeroCompte));
					break;
				case "credit":
					cQuery.where(builder.equal(virement.get("compteCrediteur"), numeroCompte));
					break;
				case "all":
					cQuery.where(
						builder.or(
							builder.equal(virement.get("compteDebiteur"), numeroCompte),
							builder.equal(virement.get("compteCrediteur"), numeroCompte)
						));
					break;
			}

			TypedQuery<Virement> query = em.createQuery(cQuery);
			listeVirements = query.getResultList();
		}
		catch (Exception ex)
		{
			Logger.getLogger(VirementDao.class.getName()).log(Level.SEVERE, null, ex);
			throw new DaoException("La liste des virements n'a pas été récupérée\n" + ex.getMessage(), ex.getCause());
		}
		finally
		{
			em.close();
		}

		return listeVirements;
	}

	/**
	 *
	 * Méthode qui suppose que le virement a été autorisé. Prend en paramètre le
	 * compte à débiter, le compte à créditer et le montant et met à jour le
	 * solde de chacun des comptes. Si la mise à jour échoue, la transaction est
	 * annulée
	 *
	 * @param virement
	 * @param montant
	 * @throws DaoException
	 */
	public void createVirement(Virement virement, double montant) throws DaoException
	{
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			Compte compteDebiteur = virement.getCompteDebiteur();
			Compte compteCrediteur = virement.getCompteCrediteur();
			double soldeDebiteur = compteDebiteur.getSolde();
			double soldeCrediteur = compteCrediteur.getSolde();

			//Modifie le solde des deux comptes
			compteDebiteur.setSolde(soldeDebiteur - montant);
			compteCrediteur.setSolde(soldeCrediteur + montant);

			//Persiste le virement en base et met à jour les compte
			em.persist(virement);

			//Mise à jour en base de données
			em.merge(compteDebiteur);
			em.merge(compteCrediteur);
			em.merge(virement);

			tx.commit();
		}
		catch (Exception ex)
		{
			// Si erreur lors de l'exécution, la transaction est annulée
			if (tx != null)
			{
				tx.rollback();
			}
			Logger.getLogger(VirementDao.class.getName()).log(Level.SEVERE, null, ex);
			throw new DaoException("Transaction annulée, les comptes n'ont pas été débités/crédités\n" + ex.getMessage(), ex.getCause());
		}
		finally
		{
			em.close();
		}
	}

}
