package fr.gtm.formation.proxibanque.servlet;

import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.service.CompteServices;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class belongs to the prensentation layer This class is an extension of
 * HttpServlet and redefine doPost(HttpServletRequest request,
 * HttpServletResponse response) and doGet(HttpServletRequest request,
 * HttpServletResponse response) methods.
 * <p>
 * The doPost and doGet methods redirect the request and response attributes to
 * a public method traitement(HttpServletRequest request, HttpServletResponse
 * response) witch : 1/ get and analyse request parameters 2/ transfer those
 * parameters to the corresponding service layer 3/ forward the request and
 * response to an url or another servlet
 * <p>
 * Calls the service layer to check if the client exist (genetaux error if
 * not)</br>
 * If the client exist get back its compte list
 *
 * @author proxibanque
 */
@WebServlet(urlPatterns =
{
	"/PreparerVirement", "/ListeComptesPourVirement"
})
//@WebServlet("/PreparerVirement")
public class PreparerVirement extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PreparerVirement()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		traitement(request, response);
	}

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
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		// Step 1 : extract request parameters		
		// Getting request information without the hostname.
		String uri = request.getRequestURI();

		// Step 2 : send request parameters to services package
		HttpSession session = request.getSession();
		Conseiller conseiller = (Conseiller) session.getAttribute("conseiller");

		session.setAttribute("success", null);
		session.setAttribute("error", null);
		session.setAttribute("warning", null);

		ArrayList<Compte> listeComptesClients = null;
		ArrayList<Compte> listeComptesTotale = null;
		try
		{
			CompteServices compteServices = new CompteServices();
			listeComptesTotale = (ArrayList<Compte>) compteServices.getAllComptes();
			listeComptesClients = (ArrayList<Compte>) compteServices.getComptesByConseiller(conseiller);
			if (listeComptesTotale == null && listeComptesClients == null)
			{
				session.setAttribute("warning", "Il n'y a pas de compte");
			}
		}
		catch (ServiceException ex)
		{
			Logger.getLogger(ListeComptes.class.getName()).log(Level.SEVERE, null, ex);
			session.setAttribute("error", ex.getMessage());
		}

		session.setAttribute("ListeComptesTotale", listeComptesTotale);
		session.setAttribute("ListeComptesClients", listeComptesClients);

		// Step 3 : response to the user
		RequestDispatcher dispatcher;
		if (uri.contains("PreparerVirement"))
		{
			dispatcher = request.getRequestDispatcher("effectuerVirement.jsp");
		}
		else if (uri.contains("ListeComptesPourVirement"))
		{
			dispatcher = request.getRequestDispatcher("listeVirements.jsp");
		}
		else
		{
			dispatcher = request.getRequestDispatcher("error.jsp");
		}

		dispatcher.forward(request, response);
	}
}
