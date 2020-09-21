package com.onlinets.utils;

import com.onlinets.pojo.FileBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ 作者：上善若水
 * @ 时间：2020-06-24 13:58
 * @ 修改：于2020年06月24日更改
 * @ 描述：文件上传/下载工具
 * @ 版本:1.0
 */
public class FilesUtil {
    private static Logger logger = LoggerFactory.getLogger(FilesUtil.class);

    private String uploadDir = "F:\\IdeaProjects\\xyqdemo\\src\\main\\webapp\\images";


    public static FileBean uploadFile(MultipartFile multipartFile, HttpServletRequest request) {
        //原文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //文件保存路径
        String path = request.getServletContext().getRealPath("/images/upload/");
        //新文件名（原文件名+UUID）
        String newFileName =  UUID.randomUUID().toString()
                .replaceAll("-", "")+"-"+originalFilename;
        FileBean fileBean=new FileBean();
        fileBean.setFilePath(path);
        fileBean.setNewFileName(newFileName);
        fileBean.setOriginName(originalFilename);
        fileBean.setFileSize(multipartFile.getSize()/1024);
        fileBean.setFileType(suffixName);
        logger.info("文件信息："+fileBean.toString());
        //创建保存路径
        File file=new File(path,newFileName);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            logger.info("文件保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBean;
    }


}
