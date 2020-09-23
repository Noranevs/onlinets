package com.onlinets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseKnowledgepoint implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String knowledgename;

    private Integer parentid;

    private Integer courseid;

    private String coursename;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKnowledgename() {
        return knowledgename;
    }

    public void setKnowledgename(String knowledgename) {
        this.knowledgename = knowledgename;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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

    public CourseKnowledgepoint() {
    }

    public CourseKnowledgepoint(Integer id, String knowledgename, Integer parentid, Integer courseid, String coursename) {
        this.id = id;
        this.knowledgename = knowledgename;
        this.parentid = parentid;
        this.courseid = courseid;
        this.coursename = coursename;
    }

    @Override
    public String toString() {
        return "CourseKnowledgepoint{" +
                "id=" + id +
                ", knowledgename='" + knowledgename + '\'' +
                ", parentid=" + parentid +
                ", courseid=" + courseid +
                ", coursename='" + coursename + '\'' +
                '}';
    }
}
