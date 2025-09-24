package cn.hbpu.zyxt.ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hbpu.zyxt.ss.entity.User;
import cn.hbpu.zyxt.ss.service.FollowUserService;



@RestController
@RequestMapping("/ss/followUser")
public class FollowUserController {

	
	@Autowired
	FollowUserService fus;
	
	@GetMapping("/followUser")
	boolean followUser(Long userId,Long followUserId)
	{
		return fus.followUser(userId, followUserId);
	}
	@GetMapping("/disFollowUser")
	boolean disFollowUser(Long userId,Long followUserId)
	{
		return fus.disFollowUser(userId, followUserId);
	}
	@GetMapping("/isFollowUser")
	boolean isFollowUser(Long userId,Long followUserId)
	{
		return fus.isFollowUser(userId, followUserId);
	}
	
	@GetMapping("/getFollowUsersByUid")
	List<User> getFollowUsersByUid(Long userId)
	{
		return fus.getFollowUserByUid(userId);
	}
	
	@GetMapping("/getUsersByFollowUid")
	List<User> getUsersByFollowUid(Long followUserId)
	{
		return fus.getUserByFollowUid(followUserId);
	}
	
	
}
