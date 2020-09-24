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
     * 查询知识点的父节点
     * */
    @RequestMapping(value = "/getParent",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getParent(){
            QueryWrapper<CourseKnowledgepoint> ckpQueryWrapper = new QueryWrapper<>();
            ckpQueryWrapper.isNull("parentid");
            List<CourseKnowledgepoint> parentList = courseKnowledgepointService.list(ckpQueryWrapper);
            if (parentList!=null){
                logger.info("查询成功，父节点有："+parentList);
                return JsonMessage.success().add("parentList",parentList);
            }else {
                return JsonMessage.error("查询失败");
            }
    }

    /*
     * 查询知识点的子节点
     * */
    @RequestMapping(value = "/getChild",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getChild(){
        QueryWrapper<CourseKnowledgepoint> ckpQueryWrapper = new QueryWrapper<>();
        ckpQueryWrapper.isNotNull("parentid");
        List<CourseKnowledgepoint> childList = courseKnowledgepointService.list(ckpQueryWrapper);
        if (childList!=null){
            logger.info("查询成功，父节点有："+childList);
            return JsonMessage.success().add("childList",childList);
        }else {
            return JsonMessage.error("查询失败");
        }
    }

    /*
     * 通过课程id查询知识点的父节点
     * */
    @RequestMapping(value = "/getParent/{coursename}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage getParent(@PathVariable("coursename") String coursename){
        QueryWrapper<CourseKnowledgepoint> ckpQueryWrapper = new QueryWrapper<>();
        ckpQueryWrapper.eq("coursename",coursename).like("parentid",null);
        List<CourseKnowledgepoint> parentList = courseKnowledgepointService.list(ckpQueryWrapper);
        if (parentList!=null){
            logger.info("查询成功，父节点有："+parentList);
            return JsonMessage.success().add("parentList",parentList);
        }else {
            return JsonMessage.error("查询失败");
        }
    }
}

