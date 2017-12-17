package fr.gtm.formation.proxibanque.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * Delete previous session information and redirect to the login page
 *
 * @author proxibanque
 */
@WebServlet("/DeconnexionConseiller")
public class DeconnexionConseiller extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeconnexionConseiller()
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
	 * This method is called by doPost or doGet methods of this class This method
	 * get a request from an Http request and :</br>
	 * 1/ get and analyse request parameters</br>
	 * 2/ transfer those parameters to the corresponding service layer</br>
	 * 3/ forward the request and response to an url or another servlet</br>
	 * <p>
	 * The method delete previous session information and redirect to the login page
	 *
	 * @param request
	 *                 : the request
	 * @param response
	 *                 : the response to dispatch
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		// Delete previous session info if exist
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null)
		{
			oldSession.invalidate();
		}
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

}
