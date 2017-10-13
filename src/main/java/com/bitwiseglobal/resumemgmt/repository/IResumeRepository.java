package com.bitwiseglobal.resumemgmt.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.bitwiseglobal.resumemgmt.entityvo.Resume;

public interface IResumeRepository extends CrudRepository<Resume, BigInteger>{

}
