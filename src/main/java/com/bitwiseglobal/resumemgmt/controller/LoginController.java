/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;

import org.jboss.logging.Logger;
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
public class LoginController {

	private static final Logger logger=Logger.getLogger(LoginController.class);
	
	@Autowired
	ResumeMgmtBD resumeMgmtBD;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		final String METHOD_NAME="LoginController.login";
		logger.debug(METHOD_NAME+"Started");
		
		
		return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout() {
		final String METHOD_NAME="LoginController.logout";
		logger.debug(METHOD_NAME+"Started");
		return "login";
	}
	
}
