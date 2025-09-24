package cn.hbpu.zyxt.ss.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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
import cn.hbpu.zyxt.ss.entity.User;
import cn.hbpu.zyxt.ss.service.UserService;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/ss/user")
public class UserController {
	@Autowired 
	UserService us;
	@PostMapping("/checkUsername")
	String checkUsername(@RequestBody User u)
	{
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("userName").eq("userName",u.getUserName());
		
		if(us.getOne(queryWrapper) != null)
		{
			return "exist";
		}
		return "error";
		
	}
	@Value("${web.uploadPath}")
	private String uploadPath;	

		
		
		@Autowired
		RedisTemplate redisTemplate;
		@GetMapping("/list")
		List<User> list()
		{
			
			return us.list();
		}
		
		@GetMapping("/getPage")
		public IPage<User> getPage(Page<User> page)
		{
			QueryWrapper<User> qw = new QueryWrapper<User>();
			
			qw.select("uid","userName","image","email","type").orderByAsc("uid");

			return us.page(page, qw);
		}
		
		@PostMapping("/regist")
		public String regist( User u,MultipartFile imgFile) 
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

			u.setImage(newFileName);
			u.setRegTime(LocalDateTime.now());
			u.setType(1);
			us.save(u);
			return "ok";
			
		}
		
		@GetMapping("/updateUserType")
		public void updateUserType(int uid)
		{
			us.updateUserType(uid);
		}
		@GetMapping("/updateUserType1")
		public void updateUserType1(int uid)
		{
			us.updateUserType1(uid);
		}
	
	@Autowired
	private RocketMQTemplate rMQT;
	@PostMapping("/login")
	public User login(@RequestBody User u)
	{
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.select("uid,userName,image,type").eq("uid",u.getUid()).eq("password",u.getPassword());
		User user = us.getOne(qw);
		if(user!=null)
		{
			rMQT.convertAndSend("login-log",user);
		}
		return user;
	}
	
	
}

