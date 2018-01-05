package com.objis.spring.demodatabase.service;

import com.objis.spring.demodatabase.domaine.Conseiller;

public interface IConseillerServices
{
	public boolean conseillerConnection(Conseiller conseiller);

	public Conseiller getConseillerByLogin(String login);
	
}
