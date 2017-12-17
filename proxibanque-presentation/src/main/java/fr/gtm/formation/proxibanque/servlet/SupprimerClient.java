package fr.gtm.formation.proxibanque.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class belongs to the prensentation layer This class is an extension of
 * HttpServlet and redefine doPost(HttpServletRequest request,
 * HttpServletResponse response) and doGet(HttpServletRequest request,
 * HttpServletResponse response) methods.
 *
 * The doPost and doGet methods redirect the request and response attributes to
 * a public method traitement(HttpServletRequest request, HttpServletResponse
 * response) witch : 1/ get and analyse request parameters 2/ transfer those
 * parameters to the corresponding service layer 3/ forward the request and
 * response to an url or another servlet
 *
 * @author proxibanque
 */
@WebServlet("/SupprimerClient")
public class SupprimerClient extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * This method is called by doPost or doGet methods of this class This method
     * get a request from an Http request and :</br>
     * 1/ get and analyse request parameters</br>
     * 2/ transfer those parameters to the corresponding service layer</br>
     * 3/ forward the request and response to an url or another servlet</br>
     *
     * @param request
     *            : the request
     * @param response
     *            : the response to dispatch
     * @throws ServletException
     * @throws IOException
     */
    public void traitement(HttpServletRequest request, HttpServletResponse response) {
	throw new UnsupportedOperationException();
    }

}
