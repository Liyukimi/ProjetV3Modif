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
import com.objis.spring.demodatabase.service.ICompteServices;
import com.objis.spring.demodatabase.service.IConseillerServices;

@Controller("/comptes")
public class ComptesController {

	@Autowired
	private ICompteServices compteServices;

}