package com.maodou.controller;

import com.maodou.domain.Result;
import com.maodou.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "登录注册")
public class LoginController {

    private final SysUserService sysUserService;

    public LoginController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(String username, String password){
        if (sysUserService.login(username, password)){
            return Result.success("登录成功");
        }
        return Result.failure(400, "用户名或密码错误");
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<String> register(String username, String password){
        if (sysUserService.register(username, password)){
            return Result.success("注册成功");
        }
        return Result.failure(400, "用户名已存在");
    }

    @Operation(summary = "退出登录")
    @RequestMapping("/logout")
    public Result<String> logout(){
        sysUserService.logout();
        return Result.success("退出成功");
    }

}
