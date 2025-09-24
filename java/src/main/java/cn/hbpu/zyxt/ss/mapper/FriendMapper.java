package cn.hbpu.zyxt.ss.mapper;

import cn.hbpu.zyxt.ss.entity.Friend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-05-09
 */
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {
	@Select("SELECT toFno,userName\n"
			+ "FROM friend \n"
			+ "left join  `user`\n"
			+ "on  toFno = uid\n"
			+ "where myUid = #{myUid}\n"
			+ "")
	public List<Friend> selectAllFriend(Integer myUid);
}
