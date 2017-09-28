package com.bitwiseglobal.resumemgmt.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitwiseglobal.resumemgmt.bd.ResumeMgmtBD;

@Controller
public class SearchController {
	@Autowired
	ResumeMgmtBD resumeMgmtBD;
	
	private static final Logger logger=Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/search-resume",method=RequestMethod.GET)
	public ModelAndView searchResume(HttpServletRequest request) {
		final String methodName="SearchController.searchResume";
		logger.debug(methodName + " started");
		
		ModelAndView mav = new ModelAndView("bw-search");
		mav.addObject("skills",resumeMgmtBD.getSkills());
		
		String selectedSkills = request.getParameter("selectedSkills");
		if(null != selectedSkills && !"".equalsIgnoreCase(selectedSkills)) {
			mav.addObject("resumes",resumeMgmtBD.getResumeBySkills(selectedSkills));
		}
		
		return mav;
	}


}
