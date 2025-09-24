package cn.hbpu.zyxt.ss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-05-11
 */
@Getter
@Setter
@TableName("stuanswer")
public class Stuanswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("qid")
    private Integer qid;

    @TableField("cid")
    private Integer cid;

    @TableField("answer")
    private String answer;

    //private int tno;
    
    private String userName;
    private String contentQuestion;

}
