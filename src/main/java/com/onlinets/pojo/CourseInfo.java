package com.onlinets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String coursename;

    private Integer teacherid;

    private String teachername;

    private String classroom;

    private String credits;

    private String credithour;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCredithour() {
        return credithour;
    }

    public void setCredithour(String credithour) {
        this.credithour = credithour;
    }

    public CourseInfo() {
    }

    public CourseInfo(Integer id, String coursename, Integer teacherid, String teachername, String classroom, String credits, String credithour) {
        this.id = id;
        this.coursename = coursename;
        this.teacherid = teacherid;
        this.teachername = teachername;
        this.classroom = classroom;
        this.credits = credits;
        this.credithour = credithour;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", teacherid=" + teacherid +
                ", teachername='" + teachername + '\'' +
                ", classroom='" + classroom + '\'' +
                ", credits='" + credits + '\'' +
                ", credithour='" + credithour + '\'' +
                '}';
    }
}
