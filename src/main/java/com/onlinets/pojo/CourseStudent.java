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
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseStudent implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer classid;

    private Integer studentid;

    private String studentname;


}
