package com.bitwiseglobal.resumemgmt.entityvo;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Skill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="skill_id", nullable=false, length=20)
	BigInteger skillId;
	
	String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "resume_skill", joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"), inverseJoinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "resume_id"))
	Set<Resume> resumes;

	public BigInteger getSkillId() {
		return skillId;
	}

	public void setSkillId(BigInteger skillId) {
		this.skillId = skillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Resume> getResumes() {
		return resumes;
	}
	
	public void setResumes(Set<Resume> resumes) {
		this.resumes = resumes;
	}
	
}
