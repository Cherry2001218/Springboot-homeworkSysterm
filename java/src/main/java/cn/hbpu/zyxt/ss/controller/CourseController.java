package cn.hbpu.zyxt.ss.controller;


import java.time.LocalDateTime;

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
import cn.hbpu.zyxt.ss.entity.Course;
import cn.hbpu.zyxt.ss.service.CommentService;
import cn.hbpu.zyxt.ss.service.CourseService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/ss/course")
public class CourseController {
	@Autowired
	CourseService cs;
	@PostMapping("/add")
	public void Add(@RequestBody Course course)
	{
		
		
		course.setCid(cs.selectAll()+1);
		course.setWdate(LocalDateTime.now()) ;
		System.out.println(course.getSno());
		System.out.println(course.getWname());
		System.out.println(course.getWtxt());
		System.out.println(course.getSno());
		System.out.println(course.getCid());
		System.out.println(course.getWdate());
		cs.AddNewCourse(course.getSno(), course.getWname(), "111", course.getCid(), course.getWdate());
	}
	
	
	
}

