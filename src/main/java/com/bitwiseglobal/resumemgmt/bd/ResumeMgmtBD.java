package com.bitwiseglobal.resumemgmt.bd;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void addResume() {
		BigInteger i = new BigInteger("1");
		User user = userRepository.findOne(i);
		Resume resume = new Resume();
		resume.setFilePath("abcd");
		resume.setName("Resume1");
		resume.setUser(user);
		resumeRepository.save(resume);
	}
	
	
	
}
