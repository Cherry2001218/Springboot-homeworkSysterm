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
 * @since 2022-05-08
 */
@Getter
@Setter
@TableName("content")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("cid")
    private Integer cid;

    @TableField("contentQuestion")
    private String contentQuestion;

    @TableField("contentPic")
    private String contentPic;

    @TableField("kindid")
    private Integer kindid;

    @TableField("trueAnswer")
    private String trueAnswer;

    @TableField("cA")
    private String cA;

    @TableField("cB")
    private String cB;

    @TableField("cC")
    private String cC;

    @TableField("cD")
    private String cD;

    @TableField("cTxt")
    private String cTxt;

    @TableId("qid")
    private Integer qid;


}
