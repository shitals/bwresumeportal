/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/rmLanding",method=RequestMethod.GET)
	public String landing(Model model) {
		final String METHO_DNAME="RMLandingController.landing";
		logger.debug(METHO_DNAME+"Started");
		
		model.addAttribute("name","[Bitwise Demo]");
		
		return "index";	
	}

	@RequestMapping(value="/addSkill",method=RequestMethod.GET)
	public String addSkill() {
		resumeMgmtBD.addSkill();
		return "temp";	
	}
	
	@RequestMapping(value="/addResume",method=RequestMethod.GET)
	public String addResume() {
		resumeMgmtBD.addResume();
		return "temp";	
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.GET)
	public String addUser() {
		resumeMgmtBD.addUser();
		return "temp";	
	}
	
}
