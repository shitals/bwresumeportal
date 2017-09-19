/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Chetan Menge
 *
 */
@Controller
public class LoginController {

	private static final Logger logger=Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		final String METHOD_NAME="LoginController.login";
		logger.debug(METHOD_NAME+"Started");
		return "login";
	}
}
