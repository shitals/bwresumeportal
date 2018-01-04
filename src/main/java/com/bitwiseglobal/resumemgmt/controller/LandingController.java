/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitwiseglobal.resumemgmt.bd.ResumeMgmtBD;


/**
 * @author Chetan Menge
 *
 */
@Controller
public class LandingController {



	private static final Logger logger=LoggerFactory.getLogger(LandingController.class);

	@Autowired
	ResumeMgmtBD resumeMgmtBD;

	@RequestMapping(value= {"/rmLanding","/"},method=RequestMethod.GET)
	public String landing(Model model) {
		final String METHO_DNAME="RMLandingController.landing: ";
		logger.debug(METHO_DNAME+"Started");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    logger.debug(METHO_DNAME+currentUserName);
		}


		model.addAttribute("name","[Bitwise Demo]");

		return "index";	
	}

}
