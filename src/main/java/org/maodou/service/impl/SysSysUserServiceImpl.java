package org.maodou.service.impl;

import org.maodou.entity.SysUser;
import org.maodou.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SysSysUserServiceImpl implements SysUserService {

    static final Logger logger = Logger.getLogger(SysSysUserServiceImpl.class.getName());

    @Override
    public int save(SysUser user) {
        logger.info("save user");
        return 0;
    }
}
