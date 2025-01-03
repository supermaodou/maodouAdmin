package org.maodou.controller;

import org.maodou.domain.Result;
import org.maodou.domain.User;
import org.maodou.exception.CustomException;
import org.maodou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        userService.save(new User());
        return Result.success("hello");
    }

    @GetMapping("/testException")
    @ResponseBody
    public Result<?> testException() {
        throw new CustomException(400, "自定义异常发生");
    }

    @GetMapping("/testError")
    @ResponseBody
    public Result<?> testError() {
        int result = 10 / 0; // 模拟系统异常
        return Result.success(result);
    }


}
