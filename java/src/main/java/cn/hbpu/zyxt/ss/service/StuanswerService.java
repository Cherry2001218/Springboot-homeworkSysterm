package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.Stuanswer;
import cn.hbpu.zyxt.ss.mapper.StuanswerMapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-11
 */
public interface StuanswerService extends IService<Stuanswer> {
	public List<Stuanswer> selectTeacherLook(int tno);
	
	public void InsertA(Stuanswer Stu);
}
