package com.onlinets.pojo;

import lombok.*;

/**
 * @ 作者：上善若水
 * @ 时间：2020-06-24 14:07
 * @ 修改：于2020年06月24日更改
 * @ 描述：封装文件实体
 * @ 版本:
 */

public class FileBean {

    private String filePath;
    private String fileType;
    private String originName;
    private Long fileSize;
    private String newFileName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public FileBean() {
    }

    public FileBean(String filePath, String fileType, String originName, Long fileSize, String newFileName) {
        this.filePath = filePath;
        this.fileType = fileType;
        this.originName = originName;
        this.fileSize = fileSize;
        this.newFileName = newFileName;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", originName='" + originName + '\'' +
                ", fileSize=" + fileSize +
                ", newFileName='" + newFileName + '\'' +
                '}';
    }
}
