package cn.hbpu.zyxt.ss.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hbpu.zyxt.ss.entity.Class;
import cn.hbpu.zyxt.ss.service.ClassService;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;


@RestController
@RequestMapping("/ss/class")
public class ClassController {
	@Value("${web.uploadPath}")
	private String uploadPath;	
	@Autowired 
	private ClassService cs;
	@GetMapping("/getSwipe")
	public IPage<Class> getSwipe()
	{
		
		Page<Class> page = new Page<Class>(1,5);
		
		QueryWrapper<Class> qw = new QueryWrapper<Class>();
		
		qw.select("time","course","pic","teachername","tno","sno").orderByAsc("sno");
		
		
		return cs.page(page, qw);
	}
	
	
	@PostMapping("/getByTitle")
	public IPage<Class> getByTitle(@RequestBody Class classs)
	{
		Page<Class> page = new Page<>(classs.getPage(),10);
		
		QueryWrapper<Class> qw = new  QueryWrapper<Class>();
		qw.select("pic","teachername","time","course","tno","sno").like("course",classs.getCourse()).orderByDesc("sno");
		
		return cs.page(page,qw);
	}
	
	@GetMapping("/getPage")
	public IPage<Class> getPage(Page<Class> page)
	{
		QueryWrapper<Class> qw = new QueryWrapper<Class>();
		
		qw.select("time","course","pic","teachername","tno","sno").orderByAsc("sno");

		return cs.page(page,qw);
	}
	
	@GetMapping("/getByIdB")
	public Class getByIdB(int sno) {
		return cs.getClassByIdB(sno);
	}
	
	@GetMapping("/getById")
	public List<Class> getById(int sno) {
		return cs.getClassById(sno);
	}
	
	
	@GetMapping("/show")
	public Class Show(int sno) {
		return cs.ShowTou(sno);
	}

	@GetMapping("/SelectTno")
	public List<Class> SelectTno (int tno) {
		return cs.SelectByUid(tno);
	}

	
	
	@PostMapping("/remove")
	public String remove (@RequestBody Class classs)
	{
		cs.remove(classs.getSno());
		return "";
	}

	
	@PostMapping("/add")
	public String add(Class classs,MultipartFile imgFile) 
	{
		
		
		String fileName = imgFile.getOriginalFilename();
		String newFileName = UUID.fastUUID().toString(true)+'.'+FileNameUtil.extName(fileName);
		
		File saveFile = new File(uploadPath+newFileName);
		
		
		try {
			imgFile.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		classs.setPic(newFileName);
		classs.setTime(LocalDateTime.now());
		classs.setSno(cs.AllFind(classs)+1);
	
		System.out.println(classs.getCourse());
		System.out.println(classs.getSno());
		System.out.println(classs.getTeachername());
		System.out.println(classs.getPic());
		System.out.println(classs.getTime());
		System.out.println(classs.getTno());
		//   public void Add(int sno,String course,String teachername ,String pic ,LocalDateTime time);
		cs.Add(classs.getTno(),classs.getSno(),classs.getCourse(),classs.getTeachername(),classs.getPic(),classs.getTime());
		return  "success";
	}
	@GetMapping("/selectFindBySno")
	 public List<Class> selectFindBySno()
	 {
		return cs.selectFindBySno();
	 }
	
	
	
}

