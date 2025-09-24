package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.Friend;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-09
 */
public interface FriendService extends IService<Friend> {

	
	public List<Friend> selectAllFriend(Integer myUid) ;
}
