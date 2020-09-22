package com.onlinets.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.UserInfoService;
import com.onlinets.utils.JsonMessage;
import com.onlinets.utils.TokenUtil;
import io.jsonwebtoken.Claims;
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
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/onlinets/user-info")
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
        logger.info("用户学号：",idcardno);
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


    /**
     * @method:查询用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUser")
    @ResponseBody
    public JsonMessage getUser(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");//获取token头部
        Claims claims = TokenUtil.parseToken(authorization);
        String id = claims.getId();
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("idcardno",id);//获取登录者学号/工号
        UserInfo one = userInfoService.getOne(wrapper);
        if (one != null){
            logger.info("查询的单个用户："+one);
            return JsonMessage.success().add("one_user",one);
        }else {
            logger.info("未查询到单个用户："+one);
            return JsonMessage.error("用户查询失败");
        }
    }

    //更新密码
    @PutMapping(value = "/updatePsw/{nowPsw}/{newPsw}")
    @ResponseBody
    public JsonMessage updatePsw(@PathVariable("nowPsw") String nowPsw,
                                 @PathVariable("newPsw")String newPsw,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        Claims claims = TokenUtil.parseToken(header);
        String id = claims.getId();
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("idcardno",id);//获取登录者学号/工号
        UserInfo one = userInfoService.getOne(wrapper);
        if(nowPsw != null && nowPsw.equals(one.getPassword()) && newPsw != null && !newPsw.equals("")){
            if (one != null){
                one.setPassword(newPsw);
                UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("password",one.getPassword());
                updateWrapper.eq("id",one.getId());
                userInfoService.update(updateWrapper);
                return JsonMessage.success().add("password",one);
            }
            else {
                return JsonMessage.error("修改密码失败，请重新修改！");
            }
        }
        return JsonMessage.error("密码不能为空");
    }

    //更新资料
    @PutMapping(value = "/updateMsg/{user}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage updateMsg(@PathVariable("user") String user, HttpServletRequest request){
        logger.info("进入了方法");
        UserInfo u = JSON.parseObject(user,UserInfo.class);
        logger.info("用户信息"+u);
        String header = request.getHeader("Authorization");
        Claims claims = TokenUtil.parseToken(header);
        String id = claims.getId();
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("idcardno",id);//获取uer_email
        UserInfo one = userInfoService.getOne(wrapper);
        if (one != null){
            one.setPhone(u.getPhone());
            logger.info(""+one.toString());
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("phone",one.getPhone());
            updateWrapper.eq("id",one.getId());
            logger.info(one.toString());
            userInfoService.update(updateWrapper);
            return JsonMessage.success().add("user_msg",one);
        }
        else {
            return JsonMessage.error("修改资料失败");
        }

    }
}

