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
import org.springframework.web.bind.annotation.PathVariable;
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
    * 通过课程id查询课程详情
    * */
    @RequestMapping(value = "/getCoursesInfo/{coursename}")
    @ResponseBody
    public JsonMessage getCoursesInfo(@PathVariable("coursename") String coursename, HttpServletRequest request){
        QueryWrapper<CourseInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("coursename",coursename);
        CourseInfo one = service.getOne(wrapper);
        if (one!=null) {
            logger.info("查到的课程详情："+one);
            return JsonMessage.success().add("courseInfo",one);
        }else {
            logger.info("未查到课程详情:"+one);
            return JsonMessage.error("查询失败");
        }
    }
}

