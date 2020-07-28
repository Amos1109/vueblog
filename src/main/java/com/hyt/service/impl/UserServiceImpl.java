package com.hyt.service.impl;

import com.hyt.entity.User;
import com.hyt.mapper.UserMapper;
import com.hyt.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AmosHong
 * @since 2020-07-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
