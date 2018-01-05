package com.objis.spring.demodatabase.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objis.spring.demodatabase.dao.IClientDao;
import com.objis.spring.demodatabase.domaine.Client;

/**
 * la classe ClientServices appartient à la couche service. Elle permet
 * d'instancier des objets ClientServices. Elle possède 5 méthodes et communique
 * avec le module dao et presentation.
 *
 * @author adminl
 */

@Service("clientServices")
public class ClientServices implements IClientServices
{

	@Autowired
	private IClientDao clientDao;

	/**
	 * méthode recuperant la liste des clients d'un conseiller par le biais son
	 * login pour cela elle fait appel à la méthode 'getClientByConseiller' du
	 * module dao
	 *
	 * @param login
	 * @return Collection de clients
	 * @throws ServiceException
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceNullWarning
	 */
	public Collection<Client> getClientsByConseiller(String login)
	{
		return clientDao.findByConseiller_login(login);		
	}

	/**
	 * méthode recuperant les informations d'un client par son idClient pour
	 * cela elle fait appel à la methode 'read' de la couche dao
	 *
	 * @param idClient
	 * @return client
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 */
	public Client getClientById(int idClient)
	{
		return clientDao.findByIdClient(idClient);
	}

	/**
	 * méthode mettant à jour les informations d'un client pour cela elle fait
	 * appel à la methode 'update' du module dao
	 *
	 * @param client
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 */
	public void updateClient(Client client)
	{
		Client existing = clientDao.findByIdClient(client.getIdClient());
		if(existing != null) client.setIdClient(existing.getIdClient());
		clientDao.save(client);
		
	}

	/**
	 * méthode permettant de créer un client pour cela elle fait appel à la
	 * methode 'create' du module dao
	 *
	 * @param client
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 */
	public void createClient(Client client)
	{
		clientDao.save(client);
		
	}

	/**
	 * méthode permettant de supprimer un client pour cela elle fait appel à la
	 * methode 'delete' du module dao
	 *
	 * @param client
	 * @throws fr.gtm.formation.proxibanque.service.exceptions.ServiceException
	 */
	public void deleteClient(Client client)
	{
		clientDao.delete(client);
		
	}

	public void deleteClientById(int id)
	{
		clientDao.deleteByIdClient(id);
		
	}
}
