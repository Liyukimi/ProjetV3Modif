package fr.gtm.formation.proxibanque.filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is a Servlet Filter witch implement class FiltreLogin</br>
 * This class allows the access to all the application pages if the user in
 * session ('avdisor') is logged in </br>
 * If the user is not logged in, the filter redirect to the login page
 *
 * @author proxibanque
 *
 */
@WebFilter("/*")
public class FiltreLogin implements Filter {

    /**
     * Default constructor.
     */
    public FiltreLogin() {

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     *
     * Method witch allows the access to all the application pages if the user
     * in session ('avdisor') is logged in </br>
     * If the user is not logged in, the filter redirect to the login page
     *
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws ServletException, IOException {

	HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;

	// Get session from request
	HttpSession session = request.getSession();
	session.setAttribute("success", null);
	session.setAttribute("error", null);

	/**
	 * If the user (corresponding to session.getAttribute("conseiller") if
	 * logged in) is logged in, the acces to all pages is granted If the
	 * user is not logged in, he's redirect to login.jsp page Some pages are
	 * allowed corresponding to login, error and css/js pages
	 */
	if ((!request.getRequestURI().equals("/ProxibanqueV3/ConnectionConseiller")
		&& !request.getRequestURI().equals("/ProxibanqueV3")
		&& !request.getRequestURI().equals("/ProxibanqueV3/login.jsp")
		&& !request.getRequestURI().equals("/ProxibanqueV3/error.jsp")
		&& !request.getRequestURI().endsWith(".js") && !request.getRequestURI().endsWith(".css"))
		& session.getAttribute("conseiller") == null) {
	    // Redirect to the 'public' page : login.jsp
	    request.setAttribute("errorAuth", "Vous devez être connecté pour accéder à cette page");
	    request.getRequestDispatcher("/login.jsp").forward(request, response);

	} else {
	    // Grant access to the request 'private' page
	    chain.doFilter(request, response);
	}
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
