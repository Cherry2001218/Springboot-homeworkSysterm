package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@Mapper
public interface ClassMapper extends BaseMapper<Class> {
	  
    @Select("select class.sno,wpic,wname,wdate,wtxt,teachername,course,cid\r\n"
    		+"from Course \r\n"
    		+"LEFT JOIN class on class.sno = Course.sno\r\n"
    		+"where class.sno = #{sno}   Order By wdate Desc ")

    List<Class> getById(int sno);
    
    @Select("SELECT class.course,class.sno,class.teachername,`user`.image,class.tno\n"
    		+ "from class\n"
    		+ "LEFT JOIN `user` ON `user`.uid = class.tno\n"
    		+ "where sno = #{sno}")
  public  Class getByIdB(int sno);
    
    
    @Select("SELECT class.sno,class.course,class.teachername,class.time,class.pic,`user`.*,`comment`.ctime,`comment`.ctxt \r\n" + 
    		"				from `comment` \r\n" + 
    		"		\r\n" + 
    		"				LEFT JOIN class on class.sno = `comment`.sno\r\n" + 
    		"				LEFT JOIN `user` on `user`.uid = `comment`.uid\r\n" + 
    		"				\r\n" + 
    		"				where class.sno =#{sno} limit 1")
    public Class selectClassBySno(int sno);
    @Select("SELECT * from class where sno = #{sno}")
	public Class selectTouBySno(int sno);
    @Insert("INSERT INTO class  ( tno,sno,course, teachername,  pic,time  )  VALUES  ( #{tno},#{sno}, #{course}, #{teachername},#{pic},#{time})")
    public void Add(int tno,int sno,String course,String teachername ,String pic ,LocalDateTime time);
    
    @Select("SELECT MAX(sno) from class")
  public int findAll(Class classs);
    
    
    @Delete("delete from class\r\n" + 
			"where sno = #{sno}")
	public void DeleteSno(int sno);

    @Select("SELECT *\n"
    		+ "from class\n"
    		+ "where tno = #{tno}")
    public List<Class> SelectByUid(int tno);
    
    @Select("SELECT count(n.cid) as nums ,c.sno,c.course\n"
    		+ "FROM course n \n"
    		+ "LEFT JOIN class c \n"
    		+ "ON c.sno = n.sno\n"
    		+ "GROUP BY n.sno")
    public List<Class> selectFindBySno();

}
