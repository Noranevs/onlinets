package com.onlinets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 金金金
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HomeworkSubmit implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer workid;

    private String studentid;

    private String studentname;

    private String submittime;

    private String submitcontent;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getSubmittime() {
        return submittime;
    }

    public void setSubmittime(String submittime) {
        this.submittime = submittime;
    }

    public String getSubmitcontent() {
        return submitcontent;
    }

    public void setSubmitcontent(String submitcontent) {
        this.submitcontent = submitcontent;
    }

    public HomeworkSubmit(Integer id, Integer workid, String studentid, String studentname, String submittime, String submitcontent) {
        this.id = id;
        this.workid = workid;
        this.studentid = studentid;
        this.studentname = studentname;
        this.submittime = submittime;
        this.submitcontent = submitcontent;
    }

    public HomeworkSubmit() {
    }

    @Override
    public String toString() {
        return "HomeworkSubmit{" +
                "id=" + id +
                ", workid=" + workid +
                ", studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", submittime=" + submittime +
                ", submitcontent='" + submitcontent + '\'' +
                '}';
    }
}
