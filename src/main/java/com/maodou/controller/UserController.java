package com.maodou.controller;

import com.maodou.domain.Result;
import com.maodou.entity.SysUser;
import com.maodou.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
public class UserController {

    private final SysUserService sysUserService;

    public UserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Operation(summary = "获取用户列表")
    @GetMapping("/list")
    public Result<List<SysUser>> list() {
        return Result.success(sysUserService.list());
    }

    @Operation(summary = "新增用户")
    @PostMapping("/add")
    public Result<Integer> save(@RequestBody SysUser user) {
        return Result.success(sysUserService.save(user));
    }

    @Operation(summary = "修改用户")
    @PostMapping("/update")
    public Result<Integer> update(@RequestBody SysUser user) {
        return Result.success(sysUserService.update(user));
    }

    @Operation(summary = "删除用户")
    @PostMapping("/delete/{id}")
    public Result<Integer> delete(@PathVariable Long id) {
        return Result.success(sysUserService.delete(id));
    }

}
