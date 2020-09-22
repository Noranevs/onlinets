package com.onlinets.pojo;

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
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseStudent implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer classid;

    private Integer studentid;

    private String studentname;

    private String teachername;

    private String coursename;

    private String classname;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public CourseStudent() {
    }

    public CourseStudent(Integer classid, Integer studentid, String studentname, String teachername, String coursename, String classname) {
        this.classid = classid;
        this.studentid = studentid;
        this.studentname = studentname;
        this.teachername = teachername;
        this.coursename = coursename;
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "CourseStudent{" +
                "classid=" + classid +
                ", studentid=" + studentid +
                ", studentname='" + studentname + '\'' +
                ", teachername='" + teachername + '\'' +
                ", coursename='" + coursename + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
