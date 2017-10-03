/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Chetan Menge
 *
 */
@Controller
public class LoginController {

	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);



	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model, String error) {
		final String METHOD_NAME="LoginController.login";
		logger.debug(METHOD_NAME+"Started");
		
		if (error != null) {
            model.addAttribute("errormsg", "Your username and password is invalid.");
		}

		return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(Model model) {
		final String METHOD_NAME="LoginController.logout";
		logger.debug(METHOD_NAME+"Started");
		model.addAttribute("message", "You have been logged out successfully.");
		
		return "login";
	}

}
