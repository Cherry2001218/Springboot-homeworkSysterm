package cn.hbpu.zyxt.ss.service;

import java.util.List;

import cn.hbpu.zyxt.ss.entity.User;



public interface FollowUserService {
	boolean followUser(Long userId,Long followUserId);
	boolean disFollowUser(Long userId,Long followUserId);
	boolean isFollowUser(Long userId,Long followUserId);
	
	
	List<User> getFollowUserByUid(Long userId);
	List<User> getUserByFollowUid(Long followUserUid);
}
