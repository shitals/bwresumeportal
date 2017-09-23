/**
 * 
 */
package com.bitwiseglobal.resumemgmt.controller;



import com.amazonaws.services.s3.model.PutObjectResult;
import com.bitwiseglobal.resumemgmt.S3Wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Chetan
 *
 */

@Controller
public class UploadController {

	@Autowired
	private S3Wrapper s3Wrapper;

	@RequestMapping(value="/uploadLanding",method=RequestMethod.GET)
	public String landing(Model model) {
		final String METHO_DNAME="RMLandingController.landing";
		
		
		
		
		return "bw-upload";	
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile[] multipartFiles,Model model) {
		for (MultipartFile multipartFile : multipartFiles) {
			System.out.println("file name="+multipartFile.getOriginalFilename());
		}
		
		System.out.println("fiels"+multipartFiles);
		List<PutObjectResult> list=s3Wrapper.upload(multipartFiles);
		System.out.println("uploaded file "+list.size());
		model.addAttribute("uploadSuccess","true");
		return "bw-upload";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
		return s3Wrapper.download(key);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws IOException {
		System.out.println("list of files"+s3Wrapper.list());
	
		model.addAttribute("name","uploaded file");
		
		return "upload";	
	}
	
	
}

