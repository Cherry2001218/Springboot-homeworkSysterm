package cn.hbpu.zyxt.ss.service;

import cn.hbpu.zyxt.ss.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
public interface UserService extends IService<User> {
	public User check(User u);
	public void updateUserType(int uid);
	public void updateUserType1(int uid);
}
