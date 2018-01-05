package com.objis.spring.demodatabase.dao;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.objis.spring.demodatabase.domaine.Compte;
import com.objis.spring.demodatabase.domaine.Conseiller;
import com.objis.spring.demodatabase.domaine.Virement;

@Repository
public interface IVirementDao extends JpaRepository<Virement, Integer>
{
	public Collection<Virement> findAllByConseiller(Conseiller conseiller);
	public Collection<Virement> findAllByCompteDebiteur(Compte compteDebiteur);
	public Collection<Virement> findAllByCompteCrediteur(Compte compteCrediteur);
}
