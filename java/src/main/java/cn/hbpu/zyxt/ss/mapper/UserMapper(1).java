package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-05-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
@Update("update `user` set type = 0 WHERE uid = #{uid}")
public void updateUserType(int uid);


@Update("update `user` set type = 1 WHERE uid = #{uid}")
public void updateUserType1(int uid);
}
