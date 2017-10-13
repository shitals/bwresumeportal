package com.bitwiseglobal.resumemgmt.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.bitwiseglobal.resumemgmt.entityvo.User;

public interface IUserRepository extends CrudRepository<User, BigInteger>{
	 User findByUserId(String user_id);

}
