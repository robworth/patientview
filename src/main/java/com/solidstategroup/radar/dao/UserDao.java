package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.user.User;

public interface UserDao {

    User getUser(String email);

}
