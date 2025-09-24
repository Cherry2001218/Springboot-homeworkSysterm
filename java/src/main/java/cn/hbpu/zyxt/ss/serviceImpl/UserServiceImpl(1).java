package cn.hbpu.zyxt.ss.serviceImpl;

import cn.hbpu.zyxt.ss.entity.User;
import cn.hbpu.zyxt.ss.mapper.StuanswerMapper;
import cn.hbpu.zyxt.ss.mapper.UserMapper;
import cn.hbpu.zyxt.ss.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private UserMapper uM;
	@Override
	public User check(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserType(int uid) {
		// TODO Auto-generated method stub
		uM.updateUserType(uid);
	}

	@Override
	public void updateUserType1(int uid) {
		// TODO Auto-generated method stub
		uM.updateUserType1(uid);
	}

}
