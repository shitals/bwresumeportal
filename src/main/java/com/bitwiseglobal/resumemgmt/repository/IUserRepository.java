package com.bitwiseglobal.resumemgmt.repository;

import org.springframework.data.repository.CrudRepository;

import com.bitwiseglobal.resumemgmt.entityvo.User;

public interface IUserRepository extends CrudRepository<User, Long>{

}
