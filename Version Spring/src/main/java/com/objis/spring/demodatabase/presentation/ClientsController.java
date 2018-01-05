package com.objis.spring.demodatabase.presentation;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.objis.spring.demodatabase.domaine.Client;
import com.objis.spring.demodatabase.domaine.Conseiller;
import com.objis.spring.demodatabase.service.IClientServices;
import com.objis.spring.demodatabase.service.IConseillerServices;

@Controller(PageFolderMapping.CLIENTS_PATH)
public class ClientsController {

	private final String CLIENTS_PATH = PageFolderMapping.CLIENTS_PATH;

	@Autowired
	private IConseillerServices conseillerServices;

	@Autowired
	private IClientServices clientServices;

	@RequestMapping("/ClientAction")
	public String clientAction(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("success", null);
		session.setAttribute("error", null);
		session.setAttribute("warning", null);
		// Step 1 : Get request parameters

		if (request.getParameter("idClientSelect") == null) {
			model.addAttribute("error", "Aucune option n'a été sélectionnée");
			return (CLIENTS_PATH + "listeClients");
		} else {

			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("idClientSelect"));

			Client client = null;

			client = clientServices.getClientById(id);
			if (client == null) {
				model.addAttribute("error", "Impossible de trouver le client");
				return (CLIENTS_PATH + "listeClients");
			}

			// Step 2 : Call another servlet depending on the 'action' form
			if (action.equals("ListeComptes")) {
				return (CLIENTS_PATH + "/ListeComptes");
			} else if (action.equals("ModifClient")) {
				model.addAttribute("client", client);
				return (CLIENTS_PATH + "modifClient");
			} else if (action.equals("ClientSelect")) {
				return (CLIENTS_PATH + "/ClientSelect");
			}
		}
		return ("error");
	}

}