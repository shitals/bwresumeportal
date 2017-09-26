package com.bitwiseglobal.resumemgmt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwiseglobal.resumemgmt.entityvo.User;
import com.bitwiseglobal.resumemgmt.repository.IUserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public User findByUserId(String user_id) {
        return userRepository.findByUserId(user_id);
    }
}
