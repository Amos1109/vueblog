package com.hyt.service.impl;

import com.hyt.entity.Blog;
import com.hyt.mapper.BlogMapper;
import com.hyt.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
