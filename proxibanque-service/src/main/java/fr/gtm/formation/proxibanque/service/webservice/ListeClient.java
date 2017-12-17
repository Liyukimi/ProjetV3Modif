/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.service.webservice;

import fr.gtm.formation.proxibanque.dao.ClientDao;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Client;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * La classe ListeClient fait partie du package webservice du module service.
 * Elle fournit un webservice permettant d'afficher la liste des clients d'un conseiller
 * donné.
 * classe non achevée
 *
 * @author adminl
 */
@Path("listeClient")
public class ListeClient
{
	/**
	 * la méthode 'selectAll' fournit la liste des clients d'un conseiller
	 * en utilisant un format de données JSON et un protocol HTTP
	 * <p>
	 * methode non achevée
	 *
	 * @param login
	 * @return collection de clients
	 */
	@GET
	@Path("/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Client> selectAll(@PathParam("login") String login)
	{
		try
		{
			ClientDao daoClient = new ClientDao();
			Collection<Client> clients = daoClient.getClientsByConseiller("Conseiller");

			return clients;
		}
		catch (DaoException ex)
		{
			Logger.getLogger(ListeClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
