package com.bitwiseglobal.resumemgmt.service;

import com.bitwiseglobal.resumemgmt.entityvo.User;

public interface UserService {
        User findByUserId(String user_id);
        User findLoggedInUser();
}
