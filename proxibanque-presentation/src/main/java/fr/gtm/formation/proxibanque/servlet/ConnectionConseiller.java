package fr.gtm.formation.proxibanque.servlet;

import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.formation.proxibanque.service.ConseillerServices;
import fr.gtm.formation.proxibanque.service.ClientServices;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class belongs to the controller layer This class is an extension of
 * HttpServlet and redefine doPost(HttpServletRequest request,
 * HttpServletResponse response) and doGet(HttpServletRequest request,
 * HttpServletResponse response) methods.
 * <p>
 * The doPost and doGet methods redirect the request and response attributes to
 * a public method traitement(HttpServletRequest request, HttpServletResponse
 * response) witch :</br>
 * 1/ get and analyse request parameters </br>
 * 2/ transfer those parameters to the corresponding service layer</br>
 * 3/ forward the request and response to an url or another servlet
 * <p>
 * Redirect the user to index.jsp if his login and password are correct </br>
 * If the login informations are not correct an error is created and the user is
 * redirect to the login page
 *
 * @author proxibanque
 */
@WebServlet("/ConnectionConseiller")
public class ConnectionConseiller extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionConseiller()
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
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
	 * <p>
	 * This method redirect the user to index.jsp if his login and password are
	 * correct </br>
	 * If the login informations are not correct an error is created and the
	 * user is redirect to the login page
	 *
	 * @param request  : the request
	 * @param response : the response to dispatch
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{

		// Delete previous session info
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null)
		{
			oldSession.invalidate();
		}

		// Step 1 : extract request parameters
		String loginServ = request.getParameter("login");
		String passwordServ = request.getParameter("password");

		// Step 2 : send request parameters to services package
		Conseiller conseiller = new Conseiller(loginServ, passwordServ);
		ConseillerServices conseillerServices = new ConseillerServices();

		// Session
		HttpSession session = request.getSession();
		ClientServices clientServices = new ClientServices();
		session.setAttribute("success", null);
		session.setAttribute("error", null);
		session.setAttribute("warning", null);

		// Step 3 : response to the user
		try
		{
			RequestDispatcher dispatcher;
			if (conseillerServices.conseillerConnection(conseiller) == true)
			{
				try
				{
					// Get all informations about the conseiller
					conseiller = conseillerServices.getConseillerByLogin(loginServ);
				}
				catch (ServiceException ex)
				{
					Logger.getLogger(ConnectionConseiller.class.getName()).log(Level.SEVERE, null, ex);
					session.setAttribute("error", ex.getMessage());
				}
				session.setAttribute("conseiller", conseiller);

				Collection<Client> listeClients = clientServices.getClientsByConseiller(conseiller.getLogin());

				session.setAttribute("listeClients", listeClients);
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			else
			{
				session.setAttribute("error", "Les informations de connexion fournies ne sont pas valides.");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		}
		catch (ServiceException ex)
		{
			Logger.getLogger(ConnectionConseiller.class.getName()).log(Level.SEVERE, null, ex);
			session.setAttribute("error", ex.getMessage());
		}
	}
}
