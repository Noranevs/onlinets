package com.onlinets.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.UserInfoService;
import com.onlinets.utils.JsonMessage;
import com.onlinets.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 金金金
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/onlinets/user")
public class UserInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    /*
     * 用户登录
     * */
    @GetMapping(value = "/login/{idcardno}/{password}")
    @ResponseBody
    public JsonMessage mlogin(@PathVariable("idcardno") String idcardno,
                              @PathVariable("password") String password,
                              HttpServletRequest request) {
        //获取系统登录的IP
        logger.info("用户邮箱：",idcardno);
        logger.info("用户密码：",password);
        if (idcardno != null && !idcardno.equals("") && password != null && !password.equals("")) {
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("idcardno", idcardno);
            wrapper.eq("password", password);
            UserInfo one = userInfoService.getOne(wrapper);
            if (one != null) {
                UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("idcardno", one.getIdcardno());
                userInfoService.update(one, updateWrapper);
                logger.info("登陆成功：" + one.toString());
                String token = TokenUtil.createToken(one.getIdcardno(), one.getName());
                logger.info("TOKEN：" + token);
                return JsonMessage.success()
                        .add("token", token);
            } else {
                return JsonMessage.error("登陆失败!");
            }
        } else {
            return JsonMessage.error("账号密码不能为空！");
        }
    }
}

