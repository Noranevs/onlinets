package com.onlinets.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onlinets.pojo.Homework;
import com.onlinets.pojo.HomeworkSubmit;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.HomeworkService;
import com.onlinets.service.HomeworkSubmitService;
import com.onlinets.service.UserInfoService;
import com.onlinets.utils.JsonMessage;
import com.onlinets.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 金金金
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/onlinets/homework-submit")
public class HomeworkSubmitController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private HomeworkSubmitService homeworkSubmitService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/submitWork/{homeworksub}")
    public JsonMessage submitWork(@PathVariable("homeworksub") String homeworkSubmit
            , HttpServletRequest request) {
        HomeworkSubmit hws = JSON.parseObject(homeworkSubmit,HomeworkSubmit.class);
        String header = request.getHeader("Authorization");
        Claims claims = TokenUtil.parseToken(header);
        String id = claims.getId();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("idcardno",id);
        UserInfo one = userInfoService.getOne(wrapper);
        if (one!=null&!one.equals("")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hws.setSubmittime(df.format(new Date()));
            boolean save = homeworkSubmitService.save(hws);
            if (save){
                logger.info("提交成功");
                String msg = "提交成功";
                return JsonMessage.success().add("msg",msg);
            }else {
                return JsonMessage.error("提交失败");
            }
        }else {
            logger.info("未获取到用户id："+id);
            return JsonMessage.error("提交失败");
        }
    }
}

