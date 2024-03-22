package com.pj.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.pj.annotation.PrintlnLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @description user
 * @date 2024/3/8 11:29:17
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @PrintlnLog
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    @RequestMapping("tokenValue")
    public SaResult tokenValue() {
        return SaResult.data(StpUtil.getTokenValue());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @RequestMapping("test")
    public String test() {
        // 获取：当前账号所拥有的角色集合
        List<String> roleList = StpUtil.getRoleList();
//        return  roleList.toString();

//// 判断：当前账号是否拥有指定角色, 返回 true 或 false
//        StpUtil.hasRole("super-admin");
//
//// 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
//        StpUtil.checkRole("super-admin");
//
//// 校验：当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
        StpUtil.checkRoleAnd("super-admin", "shop-admin");
//
//// 校验：当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
//        StpUtil.checkRoleOr("super-admin", "shop-admin");
        return roleList.toString();
    }

}
