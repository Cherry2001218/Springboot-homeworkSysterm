package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.Class;
import cn.hbpu.zyxt.ss.mapper.ClassMapper;
import cn.hbpu.zyxt.ss.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
	@Autowired
	private ClassMapper cM;

	@Override
	public List<Class> getClassById(int sno) {
		// TODO Auto-generated method stub
		return  cM.getById(sno);
	}

	@Override
	public Class getClassByIdB(int sno) {
		// TODO Auto-generated method stub
		return cM.getByIdB(sno);
	}
	@Override
	public Class ShowTou(int sno) {
		return cM.selectTouBySno(sno);
		}


	@Override
	public int AllFind(Class classs) {
		// TODO Auto-generated method stub
		return cM.findAll(classs);
	}

	@Override

	public void remove(int sno) {
		cM.DeleteSno(sno);
		
	}

	@Override
	public List<Class> SelectByUid(int tno) {
		// TODO Auto-generated method stub
		return cM.SelectByUid(tno);
	}

	@Override
	public void Add(int tno,int sno, String course, String teachername, String pic, LocalDateTime time) {
		// TODO Auto-generated method stub
		cM.Add(tno,sno, course, teachername, pic, time);
	}

	@Override
	public List<Class> selectFindBySno() {
		// TODO Auto-generated method stub
		return cM.selectFindBySno();
	}

	



	

	
}
