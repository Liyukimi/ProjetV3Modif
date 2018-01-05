package com.objis.spring.demodatabase.service;

import java.util.Collection;

import com.objis.spring.demodatabase.domaine.Compte;
import com.objis.spring.demodatabase.domaine.Conseiller;
import com.objis.spring.demodatabase.domaine.Virement;

public interface IVirementServices
{

	public Collection<Virement> getVirementsByCompte(Compte compte, String typeVirement);

	public Collection<Virement> getVirementsByConseiller(Conseiller conseiller);
	
	public String createVirement(Virement virement, int numCompteDebiteur, int numCompteCrediteur, double montant);
}
