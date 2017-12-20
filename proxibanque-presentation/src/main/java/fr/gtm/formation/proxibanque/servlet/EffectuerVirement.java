package fr.gtm.formation.proxibanque.servlet;

import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Conseiller;
import fr.gtm.formation.proxibanque.domaine.Virement;
import fr.gtm.formation.proxibanque.service.CompteServices;
import fr.gtm.formation.proxibanque.service.VirementServices;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.util.Date;
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
 * Gets numCompteDebit, numCompteCredit and montant parameters from a filled
 * form </br>
 * Calls service layer to check if the operation is allowed and depending on
 * that redirect with an error or success message
 *
 * @author proxibanque
 */
@WebServlet("/EffectuerVirement")
public class EffectuerVirement extends HttpServlet
{

	private static final long serialVersionUID = 1L;

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
	 * This method gets numCompteDebit, numCompteCredit and montant parameters
	 * from a filled form </br>
	 * The service layer check if the operation is allowed and depending on that
	 * the user is redirected to virement.jsp with an error or success message
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
		int numeroCompteDebiteur = Integer.parseInt(request.getParameter("numCompteDebit"));
		int numeroCompteCrediteur = Integer.parseInt(request.getParameter("numCompteCredit"));
		double montant = Double.parseDouble(request.getParameter("montant"));

		// Step 2 : send request parameters to services package
		VirementServices virementServices = new VirementServices();
		HttpSession session = request.getSession();
		session.setAttribute("success", null);
		session.setAttribute("error", null);
		session.setAttribute("warning", null);
		Conseiller conseiller = (Conseiller) session.getAttribute("conseiller");

		String result;
		try
		{
			Virement virement = new Virement(montant, new Date(), conseiller);

			result = virementServices.createVirement(virement, numeroCompteDebiteur, numeroCompteCrediteur, montant);
			if (result.equals("Transaction effectuée"))
			{
				session.setAttribute("success", result);
				session.setAttribute("montant", montant);
				//Récupère les nouvelles valeurs des comptes modifiés
				CompteServices compteServices = new CompteServices();
				Compte compteDebiteur = compteServices.getCompteByNumero(numeroCompteDebiteur);
				Compte compteCrediteur = compteServices.getCompteByNumero(numeroCompteCrediteur);
				session.setAttribute("compteDebiteur", compteDebiteur);
				session.setAttribute("compteCrediteur", compteCrediteur);
			}
			else
			{
				session.setAttribute("warning", result);
			}
		}
		catch (ServiceException ex)
		{
			Logger.getLogger(EffectuerVirement.class.getName()).log(Level.SEVERE, null, ex);
			session.setAttribute("error", ex.getMessage());
		}

		// Step 3 : response to the user
		RequestDispatcher dispatcher = request.getRequestDispatcher("effectuerVirement.jsp");

		dispatcher.forward(request, response);
	}
}
