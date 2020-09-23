package com.onlinets.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onlinets.pojo.CourseStudent;
import com.onlinets.pojo.Homework;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.CourseStudentService;
import com.onlinets.service.HomeworkService;
import com.onlinets.utils.JsonMessage;
import com.onlinets.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 金金金
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/onlinets/homework")
public class HomeworkController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private CourseStudentService courseStudentService;

    @Autowired
    private HomeworkService homeworkService;

    /*
     * 学生查看作业
     * */
    @RequestMapping(value = "/getHomeworks",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getHomeworks(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");//获取token头部
        Claims claims = TokenUtil.parseToken(authorization);
        String id = claims.getId();
        QueryWrapper<CourseStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("classid",id);
        CourseStudent one = courseStudentService.getOne(wrapper);
        Integer classid = one.getClassid();
        logger.info("课程id："+classid);
        if (classid != null && !classid.equals("")) {
            QueryWrapper<Homework> homeworkQueryWrapper =new QueryWrapper<>();
            homeworkQueryWrapper.eq("courseid",classid);
            List<Homework> homeworkList = homeworkService.list(homeworkQueryWrapper);
            logger.info("我的作业：" + homeworkList.toString());
            if ( homeworkList != null) {
                return JsonMessage.success().add("homeworkList", homeworkList);
            }else {
                logger.info("查询失败："+homeworkList);
                return JsonMessage.error("查询失败!");
            }
        }else {
            logger.info("未获取到课程id:"+classid);
            return JsonMessage.error("查询失败");
        }
    }
}

