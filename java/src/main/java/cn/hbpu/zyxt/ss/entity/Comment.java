package cn.hbpu.zyxt.ss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("sno")
    private Integer sno;

    @TableField("uid")
    private Integer uid;

    @TableField("ctxt")
    private String ctxt;

    @TableField("ctime")
    private LocalDateTime ctime;

    @TableId("ccid")
    private Integer ccid;

    private String userName;
    
    private String course;
    private String teachername;
    private LocalDateTime time;
    private String pic;
    private int tno;
}
