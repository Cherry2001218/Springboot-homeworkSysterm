package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.Class;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
public interface ClassService extends IService<Class> {

	List<Class> getClassById(int sno);
	 Class getClassByIdB(int sno);
	 public Class ShowTou(int sno);
	 
	 
	    public void Add(int tno,int sno,String course,String teachername ,String pic ,LocalDateTime time);
	 public int AllFind(Class classs);
	 
	 
	 

		public void remove(int sno);
		
		
		 public List<Class> SelectByUid(int tno);
		 
		 
		 public List<Class> selectFindBySno();
}
