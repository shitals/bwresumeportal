package com.bitwiseglobal.resumemgmt.bd;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwiseglobal.resumemgmt.dto.ResumeDisplayDTO;
import com.bitwiseglobal.resumemgmt.entityvo.Resume;
import com.bitwiseglobal.resumemgmt.entityvo.Skill;
import com.bitwiseglobal.resumemgmt.entityvo.User;
import com.bitwiseglobal.resumemgmt.repository.IResumeRepository;
import com.bitwiseglobal.resumemgmt.repository.ISkillRepository;
import com.bitwiseglobal.resumemgmt.repository.IUserRepository;



@Service
public class ResumeMgmtBD {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IResumeRepository resumeRepository;

	@Autowired
	ISkillRepository skillRepository;

	public void addUser() {
		User user = new User();
		user.setUserId("TestID");
		user.setPassword("test");
		user.setFirstName("Test");
		user.setLastName("test");
		user.setMiddleName("Test");
		userRepository.save(user);
	}

	public void addSkill() {
		Skill skill = new Skill();
		skill.setName("Java");
		skillRepository.save(skill);

	}

	/**
	 * will return skills for provided skill ids
	 * @param skills
	 * @return
	 */
	public List<Skill> getSkills(String skills) {
		String[] skillsList=skills.split(",");
		List<BigInteger> ids=new ArrayList<BigInteger>();
		
		for (String skill : skillsList) {
			BigInteger skillInt=new BigInteger(skill);
			ids.add(skillInt);
		}
		return (List<Skill>) skillRepository.findAll(ids);
	}

	/**
	 * will return all skills
	 * @return
	 */
	public Iterable<Skill> getSkills() {
		return skillRepository.findAll();
	}

	
	public void addResume() {
		BigInteger i = new BigInteger("4");
		BigInteger j1 = new BigInteger("43");
		BigInteger j2 = new BigInteger("44");
		User user = userRepository.findOne(i);
		
		Skill skill1 = skillRepository.findOne(j1);
		Skill skill2 = skillRepository.findOne(j2);
		
		Set<Skill> skills = new HashSet<>();
		skills.add(skill1);
		skills.add(skill2);
		
		Resume resume = new Resume();
		resume.setFilePath("abcd");
		resume.setName("Resume2");
		resume.setUser(user);
		Timestamp timestamp = new Timestamp(new Date().getTime());
		resume.setUploadTimestamp(timestamp);
		resume.setSkills(skills);
		resumeRepository.save(resume);
	}

	/**
	 * 
	 * @param resumeName
	 * @param skills
	 */
	public Resume addResume(String resumeName, String skills) {
		
		// retrieve skills
		Set<Skill> skillSet=new HashSet<Skill>(getSkills(skills));

		BigInteger i = new BigInteger("1");
		User user = userRepository.findOne(i);
		
		Resume resume = new Resume();
		resume.setFilePath(resumeName);
		resume.setName(resumeName);
		resume.setUser(user);		
		resume.setSkills(skillSet);
		Timestamp timestamp = new Timestamp(new Date().getTime());
		resume.setUploadTimestamp(timestamp);
		
		return resumeRepository.save(resume);
		
		
	}

	public Set<ResumeDisplayDTO> getResumeBySkills(String commaSeperatedSkillsStr) {

		Set<Skill> skillSet = new HashSet<>();
		for(String skillStr : commaSeperatedSkillsStr.split(",")) {
			skillSet.add(skillRepository.findOne(new BigInteger(skillStr)));
		}
		return getResumeBySkills(skillSet);
	}

	public Set<ResumeDisplayDTO> getResumeBySkills(Set<Skill> skills) {
		Set<ResumeDisplayDTO> resumeDisplayDTOSet = new TreeSet<>();
		for (Skill skill : skills) {
			for (Resume resume : skill.getResumes()) {
				resumeDisplayDTOSet.add(convertToPresentationDTO(resume));
			}
		}
		return resumeDisplayDTOSet;
	}

	private ResumeDisplayDTO convertToPresentationDTO(Resume resume) {
		ResumeDisplayDTO resumeDisplayDTO = new ResumeDisplayDTO();
		resumeDisplayDTO.setResumeId(resume.getResumeID().intValue());
		resumeDisplayDTO.setResumeName(resume.getName());
		resumeDisplayDTO.setUploadedBy(resume.getUser().getUserId());
		resumeDisplayDTO.setUploadLink(resume.getFilePath());
		resumeDisplayDTO.setUploadedTime(resume.getUploadTimestamp().toString());
		resumeDisplayDTO.setResumeSkills(convertSetToCommaSeperatedString(resume.getSkills()));
		return resumeDisplayDTO;
	}

	private String convertSetToCommaSeperatedString(Set<Skill> skills) {
		StringBuilder returnValue = new StringBuilder("");
		for (Skill skill : skills) {
			returnValue.append(skill.getName()).append(",");	
		}
		return returnValue.toString().substring(0, returnValue.length()-1);
	}
}
