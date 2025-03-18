package com.maodou.service.impl;

import com.maodou.entity.SysMenu;
import com.maodou.mapper.SysMenuMapper;
import com.maodou.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    static final Logger logger = Logger.getLogger(SysMenuServiceImpl.class.getName());

    private final SysMenuMapper sysMenuMapper;
    private final SysMenuService sysMenuService;

    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper, SysMenuService sysMenuService) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysMenuService = sysMenuService;
    }

    @Override
    public List<SysMenu> list() {
        logger.info("list menu");
        return sysMenuMapper.selectList(null);
    }

    @Override
    public int save(SysMenu menu) {
        logger.info("list save");
        return sysMenuMapper.insert(menu);
    }

    @Override
    public int update(SysMenu menu) {
        logger.info("list update");
        return sysMenuMapper.update(menu, null);
    }

    @Override
    public int delete(Long id) {
        logger.info("list delete");
        return sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> tree() {
        logger.info("list tree");
        // 查询所有菜单
        List<SysMenu> menuList = sysMenuMapper.selectList(null);
        // 构建树形结构,过滤出一级菜单
        return menuList.stream().filter(menu -> menu.getParentId() == null)
                // 构建子菜单
                .map(menu -> menuTree(menu, menuList))
                // 转换为List
                .collect(Collectors.toList());
    }

    private SysMenu menuTree(SysMenu menu, List<SysMenu> menuList) {
        // 过滤出当前菜单的子菜单
        menu.setChildren(menuList.stream().filter(m -> m.getParentId() != null && m.getParentId().equals(menu.getMenuId()))
                // 递归构建子菜单
                .map(m -> menuTree(m, menuList))
                // 转换为List
                .collect(Collectors.toList()));
        return menu;
    }

}
