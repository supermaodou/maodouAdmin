package org.maodou.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.maodou.domain.Result;
import org.maodou.entity.SysUser;
import org.maodou.exception.CustomException;
import org.maodou.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "首页")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "hello")
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        sysUserService.save(new SysUser());
        return Result.success("hello");
    }

    @Operation(summary = "testException")
    @GetMapping("/testException")
    @ResponseBody
    public Result<?> testException() {
        throw new CustomException(400, "自定义异常发生");
    }

    @Operation(summary = "testError")
    @GetMapping("/testError")
    @ResponseBody
    public Result<?> testError() {
        int result = 10 / 0; // 模拟系统异常
        return Result.success(result);
    }


}
