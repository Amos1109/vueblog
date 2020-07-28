package com.hyt.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyt.common.lang.Result;
import com.hyt.entity.Blog;
import com.hyt.service.BlogService;
import com.hyt.utils.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AmosHong
 * @since 2020-07-23
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page=new Page(currentPage,5);
        IPage pageData= blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id")Long id){
        Blog blog=blogService.getById(id);
        Assert.notNull(blog,"该博客已经被删除");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result list(@Validated@RequestBody Blog blog){
        Blog temp=null;
        Long currentId=ShiroUtil.getProfile().getId();
        if(blog.getId()!=null){
            temp=blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId()== currentId,"没有权限编辑");
        }else {
            temp=new Blog();
            temp.setUserId(currentId);
//            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog,temp,"id","userId","status","created");
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }

    @GetMapping("/blog/delete/{id}")
    public Result delete(@PathVariable(name = "id")String  id){
        boolean result = blogService.removeById(id);// 据库中为Long id
        Assert.isTrue(result,"本文章已被删除");
        return Result.success("成功删除本文章");
    }
}
