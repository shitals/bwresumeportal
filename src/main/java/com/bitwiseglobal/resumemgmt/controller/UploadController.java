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
 * @author Chetan Menge
 *
 */

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private S3Wrapper s3Wrapper;

	@Autowired
	private ResumeMgmtBD resumeMgmtBD;

	// TODO validate impact of having class level variable
	private HashMap<String, String> skillsMap;

	@RequestMapping(value = "/uploadLanding", method = RequestMethod.GET)
	public String uploadLanding(Model model) {
		final String methodName = "UploadController.uploadLanding";

		model.addAttribute("skills", getSkills());

		return "bw-upload";
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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile[] multipartFiles, @RequestParam("skills") String skills,
			Model model) {
		final String methodName = "UploadController.upload";

		String resumeName = "";
		for (MultipartFile multipartFile : multipartFiles) {
			resumeName = multipartFile.getOriginalFilename();
			logger.debug(methodName + "file name=" + multipartFile.getOriginalFilename());
		}

		logger.debug("Selected skills" + skills);

		if (!resumeName.isEmpty() && skills != null && !skills.isEmpty()) {

			try {

				// Save resume in db
				resumeMgmtBD.addResume(resumeName, skills);
				logger.info("Resume details saved sucessfully");

				// AWS S3 file upload
				
				  List<PutObjectResult> list = s3Wrapper.upload(multipartFiles);
				  logger.info("File uploaded successfully" + list.size());
				 

				model.addAttribute("uploadSuccess", "true");

			} catch (DataIntegrityViolationException e) {
				logger.error("Exceptioin occured while saving resume details" + e);
				handleException("DataIntegrityViolationException", model);
			} catch (Exception e) {
				logger.error("Exceptioin occured while saving / uploading resume" + e);
				handleException("GenericException", model);
			}

		} else {
			logger.warn("Please select atleast one skill and upload file");
			handleException("inputValidationFailed", model);
		}

		model.addAttribute("skills", getSkills());

		return "bw-upload";
	}

	private void handleException(String message, Model model) {

		logger.debug(" Exception details" + message);

		model.addAttribute("uploadSuccess", "false");

		switch (message) {
		case "inputValidationFailed":
			model.addAttribute("errorMsg", "Please select atleast one skill and upload file");
			break;
		case "DataIntegrityViolationException":
			model.addAttribute("errorMsg", "Resume already exist in system");
			break;
		case "GenericException":
			model.addAttribute("errorMsg", "Exceptioin occured while saving / uploading resume");
			break;

		}

	}

	/**
	 * 
	 * @param key
	 *            = pass key as file name e.g abc.txt
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
		return s3Wrapper.download(key);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws IOException {
		logger.debug("List of all uploaded files" + s3Wrapper.list());
		model.addAttribute("files", s3Wrapper.list());
		return "fileListing";
	}

}
