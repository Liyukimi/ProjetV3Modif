/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.servlet;

import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Virement;
import fr.gtm.formation.proxibanque.service.CompteServices;
import fr.gtm.formation.proxibanque.service.VirementServices;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListeVirements")
public class ListeVirements extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeVirements()
	{
		super();
	}

	/**
	 * @param request
	 * @param response
	 *
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		traitement(request, response);
	}

	/**
	 *
	 * @param request
	 * @param response
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		traitement(request, response);
	}

	/**
	 * This method is called by doPost or doGet methods of this class This
	 * method get a request from an Http request and :</br>
	 * 1/ get and analyse request parameters</br>
	 * 2/ transfer those parameters to the corresponding service layer</br>
	 * 3/ forward the request and response to an url or another servlet</br>
	 *
	 * @param request  : the request
	 * @param response : the response to dispatch
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		// Step 1 : extract request parameters
		int numCompte = Integer.parseInt(request.getParameter("numCompte"));

		// Step 2 : send request parameters to services package
		HttpSession session = request.getSession();
		session.setAttribute("success", null);
		session.setAttribute("error", null);
		session.setAttribute("warning", null);
		VirementServices virementServices = new VirementServices();

		//Vérifie que les compte existe
		CompteServices compteServices = new CompteServices();
		try
		{
			Compte compte = compteServices.getCompteByNumero(numCompte);
			ArrayList<Virement> listeDebits = null;
			ArrayList<Virement> listeCredits = null;
			//Récupère les listes des virements débits et crédits
			try
			{
				listeDebits = (ArrayList<Virement>) virementServices.getVirementsByCompte(compte, "debit");
				listeCredits = (ArrayList<Virement>) virementServices.getVirementsByCompte(compte, "credit");
				if (listeDebits == null && listeCredits == null)
				{
					session.setAttribute("warning", "Le compte n°" + numCompte + " n'a aucune opération récente");
				}
			}
			catch (ServiceException ex)
			{
				Logger.getLogger(ListeComptes.class.getName()).log(Level.SEVERE, null, ex);
				session.setAttribute("error", ex.getMessage());
			}

			session.setAttribute("ListeDebits", listeDebits);
			session.setAttribute("ListeCredits", listeCredits);
			session.setAttribute("numCompte", numCompte);

		}
		catch (ServiceException ex)
		{
			Logger.getLogger(ListeVirements.class.getName()).log(Level.SEVERE, null, ex);
			session.setAttribute("error", "Le compte n°" + numCompte + " n'existe pas");
		}

		// Step 3 : response to the user
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("listeVirements.jsp");
		dispatcher.forward(request, response);
	}
}
