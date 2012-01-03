package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetProfessionalUser() {
        // Get a user
        ProfessionalUser professionalUser = userDao.getProfessionalUser("test");

    }

}
