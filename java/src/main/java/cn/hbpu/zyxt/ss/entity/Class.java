package cn.hbpu.zyxt.ss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@Getter
@Setter
@TableName("class")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("sno")
    private Integer sno;

    @TableField("course")
    private String course;

    @TableField("teachername")
    private String teachername;

    @TableField("time")
    private LocalDateTime time;

    @TableField("pic")
    private String pic;
    @TableField("tno")
    private Integer tno;
    private String wname;
	private String wtxt;
	private String wpic;
	private Date wdate;
	private int cid;
   private transient Integer page;
   
   private transient Long nums;
   private String image;
}
