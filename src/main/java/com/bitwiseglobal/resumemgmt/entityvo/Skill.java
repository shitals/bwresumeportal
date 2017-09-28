package com.bitwiseglobal.resumemgmt.entityvo;

import java.math.BigInteger;
import java.util.Comparator;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (skillId == null) {
			if (other.skillId != null)
				return false;
		} else if (!skillId.equals(other.skillId))
			return false;
		return true;
	}
	
}

