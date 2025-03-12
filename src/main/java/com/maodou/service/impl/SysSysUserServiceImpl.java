package com.maodou.service.impl;

import com.maodou.entity.SysUser;
import com.maodou.service.SysUserService;
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
