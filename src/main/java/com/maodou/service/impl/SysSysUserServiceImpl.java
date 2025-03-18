package com.maodou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maodou.entity.SysUser;
import com.maodou.mapper.SysUserMapper;
import com.maodou.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SysSysUserServiceImpl implements SysUserService {

    static final Logger logger = Logger.getLogger(SysSysUserServiceImpl.class.getName());

//    @Resource
//    private SysUserMapper sysUserMapper;

    private final SysUserMapper sysUserMapper;

    public SysSysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<SysUser> list() {
        logger.info("list user");
        return sysUserMapper.selectList(null);
    }

    @Override
    public int save(SysUser user) {
        logger.info("save user");
        return sysUserMapper.insert(user);
    }

    @Override
    public int update(SysUser user) {
        logger.info("update user");
        return sysUserMapper.update(user, null);
    }

    @Override
    public int delete(Long id) {
        logger.info("delete user");
        return sysUserMapper.deleteById(id);
    }

    @Override
    public boolean login(String username, String password) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username).eq("password", password));
        return sysUser != null;
    }

    @Override
    public boolean register(String username, String password) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setUsername(username);
            sysUser.setPassword(password);
            sysUserMapper.insert(sysUser);
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        // 退出登录
    }
}
