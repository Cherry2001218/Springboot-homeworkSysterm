package cn.hbpu.zyxt.ss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hbpu
 * @since 2022-05-09
 */
@Getter
@Setter
@TableName("friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("toFno")
    private Integer toFno;

    @TableField("myUid")
    private Integer myUid;

    public String userName;

}
