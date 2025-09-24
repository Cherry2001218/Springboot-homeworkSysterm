package cn.hbpu.zyxt.ss.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mongodb.client.result.DeleteResult;

import cn.hbpu.zyxt.ss.entity.FollowUser;
import cn.hbpu.zyxt.ss.entity.User;
import cn.hbpu.zyxt.ss.mapper.UserMapper;
import cn.hbpu.zyxt.ss.service.FollowUserService;

@Service
public class FollowUserServiceImpl implements FollowUserService {

	
	@Autowired
	private MongoTemplate mnT;
	//@Autowired
	//@Qualifier("redisTemplate")
	@Resource
	private RedisTemplate redisTemplate;
	@Autowired
	private UserMapper um;
	private String Follow_User_Redis_Key = "FOLLOW_USER_";
	@Override
	public boolean followUser(Long userId, Long followUserId) {
		// TODO Auto-generated method stub
		
		FollowUser fU = new FollowUser();
		fU.setId(ObjectId.get());
		fU.setUserId(userId);
		fU.setFollowUserId(followUserId);
		fU.setCreated(System.currentTimeMillis());
		mnT.save(fU);
		
		this.redisTemplate.opsForHash().put(getFollowUserRedisKey(userId),followUserId+"",1);
		return true;
	}

	private String getFollowUserRedisKey(Long userId) {
		// TODO Auto-generated method stub
		return this.Follow_User_Redis_Key+userId;
	}

	@Override
	public boolean disFollowUser(Long userId, Long followUserId) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("userId").is(userId).and("followUserId").is(followUserId));
		
		DeleteResult rs = mnT.remove(query,FollowUser.class);
		
		if(rs.getDeletedCount()>0)
		{
			this.redisTemplate.opsForHash().delete(getFollowUserRedisKey(userId), followUserId+"");
			return true;
		}
		return false;
	}

	@Override
	public boolean isFollowUser(Long userId, Long followUserId) {
		// TODO Auto-generated method stub
		return this.redisTemplate.opsForHash().hasKey(getFollowUserRedisKey(userId), followUserId+"");
		
	}

	@Override
	public List<User> getFollowUserByUid(Long userId) {
		//我关注的
		Query query = new Query(Criteria.where("userId").is(userId));
		List<FollowUser> list = mnT.find(query,FollowUser.class);
		List<User> users = new ArrayList<User>();
		
		for(FollowUser fU  :list)
		{
			QueryWrapper<User> wrapper = new QueryWrapper();
			wrapper.select("uid,userName,image").eq("uid",fU.getFollowUserId());
			User user = um.selectOne(wrapper);
			users.add(user);
		}
		
		return users;


	}

	@Override
	public List<User> getUserByFollowUid(Long followUserUid) {
		//关注我的
		Query query = new Query(Criteria.where("followUserId").is(followUserUid));
		List<FollowUser> list = mnT.find(query,FollowUser.class);
		List<User> users = new ArrayList<User>();
		
		for(FollowUser fU  :list)
		{
			QueryWrapper<User> wrapper = new QueryWrapper();
			wrapper.select("uid,userName,image").eq("uid",fU.getUserId());
			User user = um.selectOne(wrapper);
			users.add(user);
		}
		
		return users;

	}




}
