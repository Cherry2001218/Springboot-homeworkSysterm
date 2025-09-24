package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.Content;
import cn.hbpu.zyxt.ss.entity.Course;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
public interface ContentService extends IService<Content> {
	List<Content> getAllQuestion(int cid); 
	Content BoolTrueFalse(int qid);
	
	Content getQ(int cid);
	
	
	Course getName(int cid);
	
	public void InsertAdd2(int kindid,int cid ,String contentQuestion,int qid);
	int SelectAll();
	public  void remove(int cid);
	public void InsertAdd(int kinid,int cid ,String contentQuestion,String trueAnswer,String  cA,String cB,String cC,String cD,int qid);
}
