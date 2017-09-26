/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.bitwiseglobal.resumemgmt.S3Wrapper;
import com.bitwiseglobal.resumemgmt.bd.ResumeMgmtBD;
import com.bitwiseglobal.resumemgmt.entityvo.Skill;


/**
 * @author Anand Zala
 *
 */

@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private S3Wrapper s3Wrapper;

	@Autowired
	private ResumeMgmtBD resumeMgmtBD;

	// TODO validate impact of having class level variable
	private HashMap<String, String> skillsMap;

	@RequestMapping(value = "/searchLanding", method = RequestMethod.GET)
	public String uploadLanding(Model model) {
		final String methodName = "UploadController.uploadLanding";

		model.addAttribute("skills", getSkills());

		return "bw-search";
	}

	private HashMap<String, String> getSkills() {
		final String methodName = "UploadController.getSkills";

		if (skillsMap == null || skillsMap.isEmpty()) {

			skillsMap = new HashMap<String, String>();

			Iterable<Skill> skills = resumeMgmtBD.getSkills();
			if (skills != null) {
				for (Skill skill : skills) {
					String skillId = skill.getSkillId().toString();
					skillsMap.put(skillId, skill.getName());
				}
				logger.info(methodName + "Skills found= {}" + skillsMap);
			} else {
				logger.debug(methodName + "No skills found");
			}
		}
		return skillsMap;
	}
}
