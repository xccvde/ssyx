package com.ssyx.acl.controller;

import com.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
@Api(tags = "登录接口")
//@CrossOrigin
public class IndexController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Map<String, String>> login(){
        Map<String, String> map = new HashMap<>();
        map.put("token","token-admin");
        return Result.success(map);
    }

    @ApiOperation("获取信息")
    @GetMapping("/info")
    public Result<Map<String, String>> info(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(map);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result<Map<String, String>> logout(){
        return Result.success(null);
    }

}
