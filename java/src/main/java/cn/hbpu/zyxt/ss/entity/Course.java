package cn.hbpu.zyxt.ss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("sno")
    private Integer sno;

    @TableField("wname")
    private String wname;

    @TableField("wtxt")
    private String wtxt;

    @TableField("wdate")
    private LocalDateTime wdate;

    @TableField("wpic")
    private String wpic;

    @TableField("wvideo")
    private String wvideo;

    @TableField("cid")
    private Integer cid;


}
