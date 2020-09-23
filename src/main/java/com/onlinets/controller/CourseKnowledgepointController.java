package com.onlinets.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onlinets.pojo.CourseInfo;
import com.onlinets.pojo.CourseKnowledgepoint;
import com.onlinets.pojo.CourseStudent;
import com.onlinets.pojo.UserInfo;
import com.onlinets.service.CourseInfoService;
import com.onlinets.service.CourseKnowledgepointService;
import com.onlinets.service.UserInfoService;
import com.onlinets.utils.JsonMessage;
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
@RequestMapping("/onlinets/course-knowledgepoint")
public class CourseKnowledgepointController {

    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private CourseKnowledgepointService courseKnowledgepointService;

    @Autowired
    private CourseInfoService courseInfoService;

    /*
     * 通过课程id查询知识点的父节点
     * */
    @RequestMapping(value = "/getParent/{courseid}")
    @ResponseBody
    public JsonMessage getParent(@PathVariable("courseid") String courseid, HttpServletRequest request){
        QueryWrapper<CourseKnowledgepoint> wrapper = new QueryWrapper<>();
        wrapper.eq("courseid",courseid);
        CourseKnowledgepoint ckp = courseKnowledgepointService.getOne(wrapper);
        Integer parentid = ckp.getParentid();
        if (parentid==0){
            return JsonMessage.success();
        }else {
            return JsonMessage.error("");
        }
    }
}

