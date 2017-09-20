/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitwiseglobal.resumemgmt.eo.UsersEO;
import com.bitwiseglobal.resumemgmt.vo.User;

/**
 * @author Chetan Menge
 *
 */
@Controller
public class LandingController {
	
	@Autowired
    private UsersEO usersEO;
	
	private static final Logger logger=LoggerFactory.getLogger(LandingController.class);
	
	@RequestMapping(value="/rmLanding",method=RequestMethod.GET)
	public String landing(Model model) {
		final String METHO_DNAME="RMLandingController.landing";
		logger.debug(METHO_DNAME+"Started");
		
		List<User> users=usersEO.findAll();
		for (User user : users) {
			System.out.println(METHO_DNAME +"Users Details ="+ user);
		}
		
		model.addAttribute("name","[Bitwise Demo]");
		
		return "index";	
	}

}
