package cn.hbpu.zyxt.ss.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hbpu.zyxt.ss.entity.Log;
import cn.hbpu.zyxt.ss.mapper.LogMapper;
import cn.hbpu.zyxt.ss.service.LogService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
