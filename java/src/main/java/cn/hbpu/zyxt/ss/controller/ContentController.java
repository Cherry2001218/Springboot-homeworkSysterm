package cn.hbpu.zyxt.ss.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cn.hbpu.zyxt.ss.entity.Comment;
import cn.hbpu.zyxt.ss.entity.Content;
import cn.hbpu.zyxt.ss.entity.Course;
import cn.hbpu.zyxt.ss.service.ClassService;
import cn.hbpu.zyxt.ss.service.ContentService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/ss/content")
public class ContentController {
	@Autowired 
	private ContentService cS;
	
	
	
	@GetMapping("/getQ")
	public List<Content> getQ(int cid)
	{
		return  cS.getAllQuestion(cid);
		
	}
	
	@GetMapping("/getQB")
	public Content getQB(int cid)
	{
		return  cS.getQ(cid);
		
	}
	
	
	@GetMapping("/getC")
	public Course getC(int cid)
	{
		
		return cS.getName(cid);
	}
	@PostMapping("/remove")
	public String remove (@RequestBody Content content)
	{
		cS.remove(content.getCid());
		return "";
	}
	@PostMapping("/BoolTrueFlase")
public String BoolTrueFalse(@RequestBody Content con )
	
	{
		if(con.getTrueAnswer().equals(con.getCTxt()))
		{
			return "true";
		}
		else
		{
			return "false";
		}
	}
	
	@PostMapping("/Add")
	public void Add(@RequestBody Content content)
	{
	
		content.setQid(cS.SelectAll()+1);
		System.out.println(content.toString());
		System.out.println(content.getCB());
		System.out.println(content.getKindid());
		System.out.println(content.getContentQuestion());
		cS.InsertAdd(content.getKindid(),content.getCid(), content.getContentQuestion(), content.getTrueAnswer(), "123", "123", "123", "123", content.getQid());
	}
	
	@PostMapping("/Add2")
	public void Add2(@RequestBody Content content)
	{
	
		content.setQid(cS.SelectAll()+1);
		
		System.out.println(content.getKindid());
		System.out.println(content.getContentQuestion());
		cS.InsertAdd2(content.getKindid(),content.getCid(), content.getContentQuestion(),content.getQid());
	}
	
}

