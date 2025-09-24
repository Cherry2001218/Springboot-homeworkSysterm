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
import cn.hbpu.zyxt.ss.entity.Stuanswer;
import cn.hbpu.zyxt.ss.mapper.StuanswerMapper;
import cn.hbpu.zyxt.ss.service.StuanswerService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/ss/stuanswer")
public class StuanswerController {
	
	
	@Autowired
	StuanswerService sS;
	@PostMapping("/add")
	String add(@RequestBody Stuanswer Stu)
	{
		
		
		sS.save(Stu);
		
		return "";
	}
	
	
	@GetMapping("/Show")
	public List<Stuanswer> Show(int tno)
	{
		return sS.selectTeacherLook(tno);
	}
}

