package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.Friend;
import cn.hbpu.zyxt.ss.mapper.FriendMapper;
import cn.hbpu.zyxt.ss.service.FriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-09
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

	FriendMapper fm;

	@Override
	public List<Friend> selectAllFriend(Integer myUid) {
		// TODO Auto-generated method stub
		return fm.selectAllFriend(myUid) ;
	}
	

}
