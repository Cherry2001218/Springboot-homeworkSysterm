package cn.hbpu.zyxt.ss.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cn.hbpu.zyxt.ss.entity.Friend;
import cn.hbpu.zyxt.ss.service.ClassService;
import cn.hbpu.zyxt.ss.service.FriendService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/ss/friend")
public class FriendController {
	@Autowired 
	private FriendService fs;
	@GetMapping("/list")
	List<Friend> list(Integer myUid)
	{
		return fs.selectAllFriend(myUid);
	}
}

