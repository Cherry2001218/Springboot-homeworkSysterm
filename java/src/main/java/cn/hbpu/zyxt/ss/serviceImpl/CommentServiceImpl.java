package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.Comment;
import cn.hbpu.zyxt.ss.mapper.CommentMapper;
import cn.hbpu.zyxt.ss.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
	@Autowired
	CommentMapper cM;
	@Override
	public List<Comment> getBysnod(Integer sno) {
		// TODO Auto-generated method stub
		return cM.getBySno(sno);
	}
	@Override
	public List<Comment> getByUid(int uid) {
		// TODO Auto-generated method stub
		List<Comment> list = cM.getByUid(uid);
		return list;
	}
	@Override
	public void remove(int ccid) {
		// TODO Auto-generated method stub
		cM.remove(ccid);
	}
	@Override
	public void InsertC(int ccid, int sno, int uid, String ctxt, LocalDateTime ctime) {
		// TODO Auto-generated method stub
		cM.InsertC(ccid, sno, uid, ctxt, ctime);
	}
	@Override
	public int SelectAll() {
		// TODO Auto-generated method stub
		return cM.SelectAll();
	}

	

}
