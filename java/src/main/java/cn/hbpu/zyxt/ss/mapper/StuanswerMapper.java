package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.Stuanswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-05-11
 */
@Mapper
public interface StuanswerMapper extends BaseMapper<Stuanswer> {

	@Insert("INSERT INTO stuanswer  ( qid, cid, answer )  VALUES  (  ?, ?, ? )")
	public void InsertA(Stuanswer Stu);
	@Select("SELECT stuanswer.answer ,`user`.userName ,content.contentQuestion\n"
		+ "FROM stuanswer\n"
		+ "LEFT JOIN `user` ON `user`.uid = stuanswer.cid\n"
		+ "LEFT JOIN  content ON  stuanswer.qid=content.qid\n"
		+ "LEFT JOIN course ON course.cid = content.cid\n"
		+ "LEFT JOIN class ON class.sno = course.sno \n"
		+ "WHERE class.sno = #{tno} order by content.qid DESC")
public List<Stuanswer> selectTeacherLook(int tno);
}
