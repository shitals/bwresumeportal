package com.bitwiseglobal.resumemgmt.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.bitwiseglobal.resumemgmt.entityvo.Skill;

public interface ISkillRepository extends CrudRepository<Skill, BigInteger> {

}
