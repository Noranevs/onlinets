package com.onlinets.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onlinets.pojo.CourseInfo;
import com.onlinets.pojo.CourseStudent;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.CourseInfoService;
import com.onlinets.service.CourseStudentService;
import com.onlinets.service.UserInfoService;
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
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/onlinets/course-student")
public class CourseStudentController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private CourseStudentService courseStudentService;

    @Autowired
    private UserInfoService userInfoService;

    /*
     * 学生查询课程
     * */
    @RequestMapping(value = "/getCourses",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getCourses(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");//获取token头部
        logger.info("tokennnnnnnnnnnnn:"+authorization);
        Claims claims = TokenUtil.parseToken(authorization);
        String id = claims.getId();
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("idcardno",id);
        UserInfo one = userInfoService.getOne(wrapper);
        Integer userId = one.getId();
        logger.info("用户ID："+userId);
        if (userId != null && !userId.equals("")) {
            QueryWrapper<CourseStudent> courseStudentQueryWrapper =new QueryWrapper<>();
            courseStudentQueryWrapper.eq("studentid",userId);
            List<CourseStudent> courseList = courseStudentService.list(courseStudentQueryWrapper);
            logger.info("我的课程：" + courseList.toString());
            if ( courseList != null) {
                return JsonMessage.success().add("courseList", courseList);
            }else {
                logger.info("查询失败："+courseList);
                return JsonMessage.error("查询失败!");
            }
        }else {
            logger.info("未获取用户Id:"+userId);
            return JsonMessage.error("查询失败");
        }
    }
}

