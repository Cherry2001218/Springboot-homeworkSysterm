package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.Comment;

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
public interface CommentService extends IService<Comment> {
	public List<Comment> getBysnod(Integer sno); 
	
	List<Comment> getByUid(int uid);
	void remove(int ccid);
	public void InsertC(int ccid,int sno,int uid,String ctxt,LocalDateTime ctime);
	public int SelectAll();
}
