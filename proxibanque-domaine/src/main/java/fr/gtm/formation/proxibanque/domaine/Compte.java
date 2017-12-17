/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.domaine;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Class Compte belongs to the layer domain This class allows to implement
 * Compte Object This class contains 7 attributes (numeroCompte, solde,
 * dateOuverture, typeCompte, decouvert, taux, idClientClient, This class
 * contains constructors and getters and setters
 * <p>
 */
@Entity
@NamedQueries(
	{
		@NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c")
		, @NamedQuery(name = "Compte.findByNumeroCompte", query = "SELECT c FROM Compte c WHERE c.numeroCompte = :numeroCompte")
	})
public class Compte implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "generatorCompte", sequenceName = "seq_compte", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCompte")
	@Column(name = "NUMERO_COMPTE", updatable = false, nullable = false)
	private int numeroCompte;

	private double solde;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OUVERTURE")
	private Date dateOuverture;
	@Size(max = 20)
	@Column(name = "TYPE_COMPTE")
	private String typeCompte;
	private double decouvert;
	private double taux;
	@JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT")
	@ManyToOne
	private Client client;

	@OneToMany(mappedBy = "compteCrediteur", cascade = CascadeType.ALL)
	private Collection<Virement> listeCredit;
	@OneToMany(mappedBy = "compteDebiteur", cascade = CascadeType.ALL)
	private Collection<Virement> listeDebit;

	// Constructor
	public Compte()
	{
	}

	public Compte(int numeroCompte, double solde, Date dateOuverture, String typeCompte, double decouvert, double taux)
	{
		super();
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
		this.typeCompte = typeCompte;
		this.decouvert = decouvert;
		this.taux = taux;
	}

	public Compte(int numeroCompte, double solde, Date dateOuverture, String typeCompte, double compteProperty)
	{
		super();
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
		this.typeCompte = typeCompte;
		if (typeCompte.equals("courant"))
		{
			this.decouvert = compteProperty;
			this.taux = 0;
		}
		else if (typeCompte.equals("epargne"))
		{
			this.taux = compteProperty;
			this.decouvert = 0;
		}
	}

	public Compte(int numeroCompte)
	{
		super();
		this.numeroCompte = numeroCompte;
	}

	public Collection<Virement> getListeCredit()
	{
		return listeCredit;
	}

	public void setListeCredit(Collection<Virement> listeCredit)
	{
		this.listeCredit = listeCredit;
	}

	public Collection<Virement> getListeDebit()
	{
		return listeDebit;
	}

	public void setListeDebit(Collection<Virement> listeDebit)
	{
		this.listeDebit = listeDebit;
	}

	public int getNumeroCompte()
	{
		return numeroCompte;
	}

	public void setNumeroCompte(int numeroCompte)
	{
		this.numeroCompte = numeroCompte;
	}

	public double getSolde()
	{
		return solde;
	}

	public void setSolde(double solde)
	{
		this.solde = solde;
	}

	public Date getDateOuverture()
	{
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture)
	{
		this.dateOuverture = dateOuverture;
	}

	public String getTypeCompte()
	{
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte)
	{
		this.typeCompte = typeCompte;
	}

	public double getDecouvert()
	{
		return decouvert;
	}

	public void setDecouvert(double decouvert)
	{
		this.decouvert = decouvert;
	}

	public double getTaux()
	{
		return taux;
	}

	public void setTaux(double taux)
	{
		this.taux = taux;
	}

	public Client getClient()
	{
		return client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Compte))
		{
			return false;
		}
		Compte other = (Compte) object;
		return (this.numeroCompte == other.numeroCompte);
	}

	@Override
	public String toString()
	{
		return "fr.gtm.formation.proxibanque.domaine.Compte[ numeroCompte=" + numeroCompte + " ]";
	}

}
