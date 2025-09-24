package cn.hbpu.zyxt.ss.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hbpu.zyxt.ss.entity.Class;
import cn.hbpu.zyxt.ss.entity.Comment;
import cn.hbpu.zyxt.ss.service.CommentService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/ss/comment")
public class CommentController {
	@Autowired
	CommentService cs;
	@GetMapping("/getBySno")
	List<Comment> getBySno(int sno)
	{
		return cs.getBysnod(sno);
	}
	
	
	@PostMapping("/add")
	String add(@RequestBody Comment c)
	{
		//	public void InsertC(int ccid,int sno,int uid,String ctxt,LocalDateTime ctime);
		c.setCcid(cs.SelectAll()+1);
		c.setCtime(LocalDateTime.now());
		cs.InsertC(c.getCcid(), c.getSno(), c.getUid(), c.getCtxt(), c.getCtime());
		//cs.save(c);
		return "";
	}
	
	
	@GetMapping("/getByUid")
	List<Comment>getByUid(int uid)
	{
		System.out.println(uid);
		return cs.getByUid(uid);
	}
	
	@PostMapping("/remove")
	public String remove (@RequestBody Comment comment)
	{
		cs.remove(comment.getCcid());
		return "";
	}

	
	
}

