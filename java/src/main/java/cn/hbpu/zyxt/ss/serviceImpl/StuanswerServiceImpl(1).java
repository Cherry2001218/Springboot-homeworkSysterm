package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.Stuanswer;
import cn.hbpu.zyxt.ss.mapper.ClassMapper;
import cn.hbpu.zyxt.ss.mapper.StuanswerMapper;
import cn.hbpu.zyxt.ss.service.StuanswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-11
 */
@Service
public class StuanswerServiceImpl extends ServiceImpl<StuanswerMapper, Stuanswer> implements StuanswerService {
	@Autowired
	private StuanswerMapper sM;
	@Override
	public List<Stuanswer> selectTeacherLook(int tno) {
		// TODO Auto-generated method stub
		return sM.selectTeacherLook(tno);
	}
	@Override
	public void InsertA(Stuanswer Stu) {
		// TODO Auto-generated method stub
		sM.InsertA(Stu);
	}

}
