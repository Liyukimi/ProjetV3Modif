package fr.gtm.formation.proxibanque.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import fr.gtm.formation.proxibanque.service.ClientServices;
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
 * Get an idClient in parameter and depending on the 'action' specified in the
 * form witch called it, redirect the request to the correct servlet
 *
 * @author proxibanque
 */
@WebServlet("/ClientAction")
public class ClientAction extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientAction()
	{
		super();
	}

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
	 * <p>
	 * This method get an idClient in parameter and depending on the 'action'
	 * specified in the form witch called it, redirect the request to the
	 * correct servlet
	 *
	 * @param request  : the request
	 * @param response : the response to dispatch
	 * @throws ServletException
	 * @throws IOException
	 */
	public void traitement(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		{
			RequestDispatcher dispatcher;
			HttpSession session = request.getSession();
			session.setAttribute("success", null);
			session.setAttribute("error", null);
			session.setAttribute("warning", null);
			// Step 1 : Get request parameters

			if (request.getParameter("idClientSelect") == null)
			{
				session.setAttribute("errorSelect", "Aucune option n'a été sélectionnée");
				dispatcher = getServletContext().getRequestDispatcher("/listeClients.jsp");
				dispatcher.forward(request, response);
			}
			else
			{

				String action = request.getParameter("action");
				int id = Integer.parseInt(request.getParameter("idClientSelect"));
				ClientServices clientServices = new ClientServices();
				Client client = null;
				try
				{
					client = clientServices.getClientById(id);
				}
				catch (ServiceException ex)
				{
					Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
					session.setAttribute("error", ex.getMessage());
				}

				// Step 2 : Call another servlet depending on the 'action' form
				if (action.equals("ListeComptes"))
				{
					dispatcher = getServletContext().getRequestDispatcher("/ListeComptes");
					dispatcher.forward(request, response);
				}
				else if (action.equals("ModifClient"))
				{
					session.setAttribute("client", client);
					dispatcher = getServletContext().getRequestDispatcher("/modifClient.jsp");
					dispatcher.forward(request, response);
				}
				else if (action.equals("ClientSelect"))
				{
					dispatcher = getServletContext().getRequestDispatcher("/ClientSelect");
					dispatcher.forward(request, response);
				}
			}
		}
	}

}
