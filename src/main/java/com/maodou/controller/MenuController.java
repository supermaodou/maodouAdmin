package com.maodou.controller;

import com.maodou.domain.Result;
import com.maodou.entity.SysMenu;
import com.maodou.service.SysMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final SysMenuService sysMenuService;

    public MenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @GetMapping("/list")
    public Result<List<SysMenu>> list() {
        return Result.success(sysMenuService.list());
    }

    @GetMapping("/tree")
    public Result<List<SysMenu>> tree() {
        return Result.success(sysMenuService.tree());
    }

}
