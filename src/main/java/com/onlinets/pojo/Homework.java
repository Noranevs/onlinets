package com.onlinets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class Homework implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer courseid;

    private String coursename;

    private String teachername;

    private String worktitle;

    private String workcontent;

    private LocalDateTime workstart;

    private LocalDateTime workend;

    private String classname;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getWorktitle() {
        return worktitle;
    }

    public void setWorktitle(String worktitle) {
        this.worktitle = worktitle;
    }

    public String getWorkcontent() {
        return workcontent;
    }

    public void setWorkcontent(String workcontent) {
        this.workcontent = workcontent;
    }

    public LocalDateTime getWorkstart() {
        return workstart;
    }

    public void setWorkstart(LocalDateTime workstart) {
        this.workstart = workstart;
    }

    public LocalDateTime getWorkend() {
        return workend;
    }

    public void setWorkend(LocalDateTime workend) {
        this.workend = workend;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Homework() {
    }

    public Homework(Integer id, Integer courseid, String coursename, String teachername, String worktitle, String workcontent, LocalDateTime workstart, LocalDateTime workend, String classname) {
        this.id = id;
        this.courseid = courseid;
        this.coursename = coursename;
        this.teachername = teachername;
        this.worktitle = worktitle;
        this.workcontent = workcontent;
        this.workstart = workstart;
        this.workend = workend;
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", courseid=" + courseid +
                ", coursename='" + coursename + '\'' +
                ", teachername='" + teachername + '\'' +
                ", worktitle='" + worktitle + '\'' +
                ", workcontent='" + workcontent + '\'' +
                ", workstart=" + workstart +
                ", workend=" + workend +
                ", classname='" + classname + '\'' +
                '}';
    }
}
