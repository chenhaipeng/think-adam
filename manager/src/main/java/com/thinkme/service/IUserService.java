package com.thinkme.service;

import com.thinkme.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	boolean deleteAll();

	public List<User> selectListBySQL();
}