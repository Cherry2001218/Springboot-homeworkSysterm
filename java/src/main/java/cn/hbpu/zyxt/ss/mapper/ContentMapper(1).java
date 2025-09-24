package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.Content;
import cn.hbpu.zyxt.ss.entity.Course;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface ContentMapper extends BaseMapper<Content> {
	@Select("SELECT * \r\n"
			+ "from content\r\n"
			+ "where cid = #{cid}")
	List<Content> getQByCid(int cid);
	
	@Select("SELECT * from content where qid=#{qid}")
	Content getQAnser(int qid);
	
	@Select("SELECT * \r\n"
			+ "from content\r\n"
			+ "where cid = #{cid} limit 1")	
	Content getQByCidB(int cid);
	
	
	@Select("SELECT wname FROM course WHERE cid = #{cid}")
	Course getName(int  cid);
	
	
	@Select("SELECT count(*) from content")
	int SelectAll();
	
	@Delete("DELETE from course\r\n" + 
			"where cid = #{cid}")
	public  void remove(int cid);
	
	@Insert("INSERT INTO content ( kindid,cid,contentQuestion,qid) VALUES ( #{kindid},#{cid},#{contentQuestion}, #{qid})")
	public void InsertAdd2(int kindid,int cid ,String contentQuestion,int qid);
	@Insert("INSERT INTO content ( kindid,cid,contentQuestion, trueAnswer,cA ,cB,cC,cD ,qid) VALUES ( #{kindid},#{cid},#{contentQuestion}, #{trueAnswer},#{cA} ,#{cB},#{cC},#{cD} ,#{qid})")
	public void InsertAdd(int kindid,int cid ,String contentQuestion,String trueAnswer,String  cA,String cB,String cC,String cD,int qid);
}
