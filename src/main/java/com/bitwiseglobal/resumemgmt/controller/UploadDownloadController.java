/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bitwiseglobal.resumemgmt.S3Wrapper;
import com.bitwiseglobal.resumemgmt.bd.ResumeMgmtBD;
import com.bitwiseglobal.resumemgmt.entityvo.Resume;
import com.bitwiseglobal.resumemgmt.entityvo.Skill;

/**
 * @author Chetan Menge
 *
 */

@Controller
public class UploadDownloadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);
	private static final String ERROR_MSG = "errorMsg";

	@Autowired
	private S3Wrapper s3Wrapper;

	@Autowired
	private ResumeMgmtBD resumeMgmtBD;

	@Value("${maxFileSize}")
	private String maxFileSize;

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

		try {

			String resumeName = "";
			for (MultipartFile multipartFile : multipartFiles) {

				resumeName = multipartFile.getOriginalFilename();
				logger.debug(methodName + "file name=" + multipartFile.getOriginalFilename());
				logger.debug(methodName + "File size=" + multipartFile.getSize());
				if (multipartFile.getSize() > getFileSize()) {
					FileSizeLimitExceededException e = new FileSizeLimitExceededException("LimitExceed", 0, 0);
					throw e;
				}
			}

			logger.debug("Selected skills" + skills);

			if (!resumeName.isEmpty() && skills != null && !skills.isEmpty()) {

				// Save resume in db
				Resume resume = resumeMgmtBD.addResume(resumeName, skills);

				logger.info("Resume details saved sucessfully");

				// AWS S3 file upload
				List<PutObjectResult> list = s3Wrapper.upload(multipartFiles, resume.getResumeID().toString());
				logger.info("File uploaded successfully" + list.size());

				model.addAttribute("uploadSuccess", "true");

			} else {
				logger.warn("Please select atleast one skill and upload file");
				handleException("inputValidationFailed", model);
			}

		} catch (FileSizeLimitExceededException e) {
			logger.error("File exceeds its maximum permitted size" + e);
			handleException("FileSizeLimitExceededException", model);
		} catch (DataIntegrityViolationException e) {
			logger.error("Exceptioin occured while saving resume details" + e);
			handleException("DataIntegrityViolationException", model);
		} catch (Exception e) {
			logger.error("Exceptioin occured while saving / uploading resume" + e);
			handleException("GenericException", model);
		}
		model.addAttribute("skills", getSkills());

		return "bw-upload";
	}

	/**
	 * 
	 * @param key
	 *            = pass key as file name e.g abc.txt
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam String key, @RequestParam String fileName) throws IOException {

		ResponseEntity<byte[]> entity = null;
		try {

			if (key != null && !key.isEmpty() && fileName != null && !fileName.isEmpty()) {
				entity = s3Wrapper.download(key, fileName);
				logger.info("File downloaded successfully");
			} else {
				logger.warn("Null or Empty input");
			}
		} catch (AmazonS3Exception amazonS3Exception) {
			logger.warn("Exception while downloding file" + amazonS3Exception);
		} catch (Exception e) {
			logger.warn("Exception while downloding file" + e);
		}

		return entity;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws IOException {
		logger.debug("List of all uploaded files" + s3Wrapper.list());
		model.addAttribute("files", s3Wrapper.list());
		return "fileListing";
	}

	private int getFileSize() {
		return Integer.parseInt(maxFileSize) * 1024 * 1024;
	}

	private void handleException(String message, Model model) {

		logger.debug(" Exception details" + message);

		model.addAttribute("uploadSuccess", "false");

		switch (message) {
		case "inputValidationFailed":
			model.addAttribute(ERROR_MSG, "Please select atleast one skill and upload file");
			break;
		case "DataIntegrityViolationException":
			model.addAttribute(ERROR_MSG, "Resume already exist in system");
			break;
		case "GenericException":
			model.addAttribute(ERROR_MSG, "Exceptioin occured while saving / uploading resume");
			break;
		case "FileSizeLimitExceededException":
			model.addAttribute(ERROR_MSG, "File exceeds its maximum permitted size of " + maxFileSize + "Mb");
			break;
		case "FileNotFound":
			model.addAttribute(ERROR_MSG, "File Not Found");
			break;

		}
	}

}
