package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.service.UserManager;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserManagerImpl implements UserManager, UserDetailsService {

    private UserDao userDao;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        // Pull the user from the DAO
        User user = userDao.getProfessionalUser(email);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User not found with email address " + email);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
