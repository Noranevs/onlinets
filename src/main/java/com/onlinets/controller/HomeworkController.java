package com.onlinets.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onlinets.pojo.CourseInfo;
import com.onlinets.pojo.CourseStudent;
import com.onlinets.pojo.Homework;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.CourseInfoService;
import com.onlinets.service.CourseStudentService;
import com.onlinets.service.HomeworkService;
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
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/onlinets/homework")
public class HomeworkController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private CourseInfoService courseInfoService;

    /*
     * 学生查看作业
     * */
    @RequestMapping(value = "/getHomeworks/{coursename}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getHomeworks(@PathVariable("coursename") String coursename, HttpServletRequest request){
        QueryWrapper<CourseInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("coursename",coursename);
        CourseInfo one = courseInfoService.getOne(wrapper);
        Integer classid = one.getId();
        logger.info("课程id：",classid);
        if (classid!=null && !classid.equals("")) {
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
            return JsonMessage.error("查询失败");
        }
    }

    /*
    * 查看作业详情
    * */
    @RequestMapping(value = "/getWorkContent/{worktitle}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getWorkContent(@PathVariable("worktitle") String worktitle,HttpServletRequest request){
        QueryWrapper<Homework> wrapper = new QueryWrapper<>();
        wrapper.eq("worktitle",worktitle);
        Homework one = homeworkService.getOne(wrapper);
        if (one != null){
            logger.info("查询作业详情："+one);
            return JsonMessage.success().add("workContent",one);
        }else {
            logger.info("未查询到作业详情："+one);
            return JsonMessage.error("作业查询失败");
        }
    }
}

