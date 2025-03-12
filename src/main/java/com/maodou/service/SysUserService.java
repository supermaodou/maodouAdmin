package com.maodou.service;

import com.maodou.entity.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> list();

    int save(SysUser user);

    int update(SysUser user);

    int delete(Long id);

}
