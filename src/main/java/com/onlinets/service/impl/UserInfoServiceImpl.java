package com.onlinets.service.impl;

import com.onlinets.pojo.UserInfo;
import com.onlinets.dao.UserInfoMapper;
import com.onlinets.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 金金金
 * @since 2020-09-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
