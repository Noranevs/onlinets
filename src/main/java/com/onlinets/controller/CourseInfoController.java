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
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/onlinets/course-info")
public class CourseInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private CourseInfoService service;

    @Autowired
    private CourseStudentService studentService;

    /*
    * 查询课程详情
    * */
    @RequestMapping(value = "/getCoursesInfo")
    @ResponseBody
    public JsonMessage getCoursesInfo(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");//获取token头部
        Claims claims = TokenUtil.parseToken(authorization);
        String id = claims.getId();
        QueryWrapper<CourseStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);//获取课程id
        CourseStudent one = studentService.getOne(wrapper);
        Integer userId = one.getClassid();
        logger.info("用户ID："+userId);
        if (userId != null && !userId.equals("")) {
            QueryWrapper<CourseInfo> courseQueryWrapper =new QueryWrapper<>();
            courseQueryWrapper.eq("user_id",userId);
            List<CourseInfo> courseList = service.list(courseQueryWrapper);
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

