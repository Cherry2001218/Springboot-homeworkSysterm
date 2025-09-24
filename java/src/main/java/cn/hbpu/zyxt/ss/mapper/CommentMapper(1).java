package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
	@Select("SELECT class.sno,class.course,class.teachername,class.time,class.pic,`user`.*,`comment`.ctime,`comment`.ctxt \r\n" + 
			"				from `comment`\r\n" + 
			"\r\n" + 
			"				LEFT JOIN class on class.sno = `comment`.sno \r\n" + 
			"				LEFT JOIN `user` on `user`.uid = `comment`.uid\r\n" + 
			"				where class.sno =#{sno} order by ccid desc")
	List<Comment> getBySno(int sno);

	@Select("select max(ccid)\r\n" + 
			"from comment")
	int allNum(Comment comment);
	
	
	
	@Select("SELECT `comment`.ctxt,`comment`.sno,`comment`.uid,`comment`.ctime,`comment`.ccid, class.course,class.teachername,class.time,class.pic,class.tno\n"
			+ "from `comment`\n"
			+ "LEFT JOIN class ON class.sno = `comment`.sno\n"
			+ "where uid = #{uid} order by ccid desc")
	
	public List<Comment> getByUid(int uid);
	@Delete("DELETE from `comment`\r\n" + 
			"where ccid = #{ccid}")
	public  void remove(int ccid);

	@Select("SELECT count(*)\n"
			+ "from `comment`\n"
			+ "")
	public int SelectAll();
	@Insert("INSERT INTO comment  ( ccid, sno, uid, ctxt, ctime)  VALUES  ( #{ccid}, #{sno}, #{uid}, #{ctxt}, #{ctime} )")
	public void InsertC(int ccid,int sno,int uid,String ctxt,LocalDateTime ctime);
}
