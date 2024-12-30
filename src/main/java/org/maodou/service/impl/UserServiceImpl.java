package org.maodou.service.impl;

import org.maodou.controller.IndexController;
import org.maodou.domain.User;
import org.maodou.service.UserService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public int save(User user) {
        logger.info("save user");
        return 0;
    }
}
