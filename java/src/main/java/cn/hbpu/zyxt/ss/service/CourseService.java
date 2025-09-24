package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.Course;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
public interface CourseService extends IService<Course> {
	public void AddNewCourse(int sno,String wname,String wtxt,int cid,LocalDateTime wdate);
	public int selectAll();
}
