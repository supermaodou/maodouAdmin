package com.maodou.service;

import com.maodou.entity.SysMenu;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> list();

    int save(SysMenu menu);

    int update(SysMenu menu);

    int delete(Long id);

    List<SysMenu> tree();
}
