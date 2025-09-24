package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.Course;
import cn.hbpu.zyxt.ss.mapper.ClassMapper;
import cn.hbpu.zyxt.ss.mapper.CourseMapper;
import cn.hbpu.zyxt.ss.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
	@Autowired
	private CourseMapper cM;

	@Override
	public int selectAll() {
		// TODO Auto-generated method stub
		return cM.selectAll();
	}

	@Override
	public void AddNewCourse(int sno, String wname, String wtxt, int cid, LocalDateTime wdate) {
		// TODO Auto-generated method stub
		cM.AddNewCourse(sno, wname, wtxt, cid, wdate);
	}
	
}
