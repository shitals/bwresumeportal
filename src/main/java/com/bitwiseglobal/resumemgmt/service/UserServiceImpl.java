package com.bitwiseglobal.resumemgmt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bitwiseglobal.resumemgmt.entityvo.User;
import com.bitwiseglobal.resumemgmt.repository.IUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public User findByUserId(String user_id) {
        return userRepository.findByUserId(user_id);
    }

	@Override
	public User findLoggedInUser() {
		final String METHOD_NAME="UserServiceImpl.findLoggedInUser ";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    logger.debug(METHOD_NAME +" Current user :- "+currentUserName);
		    return userRepository.findByUserId(currentUserName);
		}
		logger.warn(METHOD_NAME +" Did not find active user. Returning null ");
		return null;
	}
}
