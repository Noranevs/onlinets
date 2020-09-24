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
    @RequestMapping(value = "/getParent/{coursename}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getParent(@PathVariable("coursename") String coursename,HttpServletRequest request){
        QueryWrapper<CourseKnowledgepoint> wrapper = new QueryWrapper<>();
        wrapper.eq("coursename",coursename);
        CourseKnowledgepoint ckp = courseKnowledgepointService.getOne(wrapper);
        Integer classid = ckp.getCourseid();
        logger.info("课程ID："+classid);
        if (classid != null && !classid.equals("")) {
            QueryWrapper<CourseKnowledgepoint> ckpQueryWrapper = new QueryWrapper<>();
            wrapper.eq("parentid",0);
            List<CourseKnowledgepoint> parentList = courseKnowledgepointService.list(ckpQueryWrapper);
            if (parentList!=null){
                logger.info("查询成功，父节点有："+parentList);
                return JsonMessage.success().add("pointList",parentList);
            }else {
                return JsonMessage.error("查询失败");
            }
        }else {
            logger.info("未获取课程id："+classid);
            return JsonMessage.error("查询失败");
        }
    }


}

