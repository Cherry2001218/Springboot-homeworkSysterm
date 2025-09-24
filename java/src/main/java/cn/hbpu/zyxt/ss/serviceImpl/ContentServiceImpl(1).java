package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.Content;
import cn.hbpu.zyxt.ss.entity.Course;
import cn.hbpu.zyxt.ss.mapper.ContentMapper;
import cn.hbpu.zyxt.ss.service.ContentService;
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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
	@Autowired
	private ContentMapper iM;
	public List<Content> getAllQuestion(int cid) {
		// TODO Auto-generated method stub
		return iM.getQByCid(cid);
	}
	public Content BoolTrueFalse(int qid) {
		// TODO Auto-generated method stub
		return iM.getQAnser(qid);
		
	}
	public Content getQ(int cid) {
		// TODO Auto-generated method stub
		return iM.getQByCidB(cid);
	}
	@Override
	public Course getName(int cid) {
		// TODO Auto-generated method stub
		return iM.getName(cid);
	}
	@Override
	public int SelectAll() {
		// TODO Auto-generated method stub
		return iM.SelectAll();
	}
	@Override
	public void InsertAdd(int kindid,int cid, String contentQuestion, String trueAnswer, String cA, String cB, String cC,String cD, int qid) 
	{
		iM.InsertAdd(kindid,cid, contentQuestion, trueAnswer, cA, cB, cC, cD, qid);
		
	}
	@Override
	public void InsertAdd2(int kindid, int cid, String contentQuestion, int qid) {
		// TODO Auto-generated method stub
		iM.InsertAdd2(kindid, cid, contentQuestion, qid);
	}
	@Override
	public void remove(int cid) {
		// TODO Auto-generated method stub
		iM.remove(cid);
	}
	
}
