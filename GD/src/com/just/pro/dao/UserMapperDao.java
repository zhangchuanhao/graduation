package com.just.pro.dao;

import com.just.pro.bean.User;

public interface UserMapperDao {
	/**
	 * 通过ID查找用户
	 * @param name
	 * @return
	 */
	public User findById(String id);
}
