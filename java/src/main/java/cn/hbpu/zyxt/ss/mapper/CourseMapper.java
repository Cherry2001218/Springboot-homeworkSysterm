package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;

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
public interface CourseMapper extends BaseMapper<Course> {
@Select("SELECT count(*)\n"
		+ "from course")
public int selectAll();

@Insert("INSERT INTO course (sno,wname,wtxt,cid,wdate)  VALUES  (#{sno},#{wname},#{wtxt},#{cid},#{wdate})")
public void AddNewCourse(int sno,String wname,String wtxt,int cid,LocalDateTime wdate);
}
