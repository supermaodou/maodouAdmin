package com.maodou.controller;

import com.maodou.domain.Result;
import com.maodou.entity.SysMenu;
import com.maodou.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Tag(name = "菜单管理")
public class MenuController {

    private final SysMenuService sysMenuService;

    public MenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @Operation(summary = "获取菜单列表")
    @GetMapping("/list")
    public Result<List<SysMenu>> list() {
        return Result.success(sysMenuService.list());
    }

    @Operation(summary = "获取菜单树")
    @GetMapping("/tree")
    public Result<List<SysMenu>> tree() {
        return Result.success(sysMenuService.tree());
    }

}
